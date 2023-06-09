---
title: Architecture de notifications de modification des equipes dans le catalogue
type: adr
date:  2022-10-26
---
# Architecture d'émission des notifications d'évènement sur le catalogue

## Contexte
Les notifications d'évènements survenus sur le catalogue d'équipe doivent être envoyées 1 seule fois par évènement à chaque abonné.
Actuellement le traitement de détection d'évènements survenu sur le catalogue et les notifications émises sont traités par chacune des instances de l'application (donc il y a autant de notifications émises qu'il y a d'instance de l'application). 

## Solutions identifiées

1. Leader election entre les pods - Gestion de verrou (sémaphore) 
   1. utiliser une librairie permettant d'élire et connaitre l'élu pour que seul l'élu notifie les abonnés
      exemples:

      https://github.com/kubernetes-client/java/blob/master/examples/examples-release-15/src/main/java/io/kubernetes/client/examples/LeaderElectionExample.java

      https://github.com/Netflix-Skunkworks/kubernetes-client-java/blob/master/examples/src/main/java/io/kubernetes/client/examples/LeaderElectionExample.java

      fabric8: https://github.com/fabric8io/kubernetes-client/blob/master/kubernetes-examples/src/main/java/io/fabric8/kubernetes/examples/LeaderElectionExamples.java

1. deplacer le code du job concourse dans le composant actuel ou un autre microservice qui réagi au webhook gitlab (approche push) 

   1. Ajout endpoint et traitement de rafraichissement des équipes et notifications dans le composant actuel (déclenchée par un cronjob)
   ```mermaid
         
   graph LR    
     subgraph TMJ-repo-equipes
     Equipes(Repo equipes)
     Clients(Repo abonnés notifs)
     end
     subgraph Concourse
     JobClients(Job MaJ abonnés notifs)
     end
     subgraph OI185
     EndpointRefreshEquipes(Endpoint de rafraichissement des equipes et notifs des abonnés)
     ConfigMapEquipes(Equipes)
     ConfigMapClients(Abonnés notifs)
     ApiNotif(API notification)
     end
     subgraph EquipeGitlab
     GestionRunners(Gestion runners)
     GestionGroupes(Gestion groupes)
     end
     subgraph EquipeConcourse
     GestionWorkers(Gestion workers)
     end
     subgraph CronJobK8s
     JobExecRefreshEtNotifs(Job d'execution de rafraichissement)
     JobExecRefreshEtNotifs --> EndpointRefreshEquipes
     end

     Clients -->|Webhook| JobClients
     JobClients -->|MaJ configMap| ConfigMapClients
        
     EndpointRefreshEquipes -->|1 - Recherche MaJ - lecture repo git| ConfigMapEquipes
     EndpointRefreshEquipes -->|2 - Maj ConfigMap | ConfigMapEquipes
     EndpointRefreshEquipes -->|3 - Lance notif| ApiNotif
     ApiNotif -->|Webhook| GestionRunners
     ApiNotif -->|Webhook| GestionGroupes
     ApiNotif -->|Webhook| GestionWorkers

   ```


   1. Nouveau microservice API de rafraichissement des équipes et notifications d'évènements (déclenchée par un webhook depuis le repo Gitlab des equipes ou par un CronJob )
   

   (+ [optionnel] utilisation d'un cache distribué plutot que le FileSystem alimenté par ConfigMap)


## Décision:
L'architecture retenue est la suivante (2.2):
**Ajouter un endpoint de rafraichissement des équipes et notifications d'évènements dans le composant API, qui sera déclenché par un CronJob à fréquence régulière)**

Cette architecture permet d'être modulaire et de s'abstraire au mieux de la plateforme sur laquelle le composant est déployé.
L'approche pull, à savoir l'appel à fréquence régulière (plutôt qu'être appelé par gitlab via un webhook) sécurise l'éventuelle défaillance de gitlab.
Externaliser ce traitement dans un autre microservice ne nous apparait pas aujourd'hui nécessaire (on considère qu'un domaine doit etre en mesure d'émettre ses propres évènements).
