---
title: Notifications de modification des equipes dans le catalogue
type: adr
date:  2022-10-26
---
# Techno de notifications d'évènements sur le catalogue

## Contexte

Permettre à des composants/microservices d'être notifiés lors des contributions aux équipes (composant de création d'équipes dans Gitlab, dans Concourse, dans Rancher, org/spaces TAS, arbo Grafana, etc. )

## Critères de choix de la solution:

- Etre sûr que tous les abonnés ont eu le message: OUI
- Ordre des messages respectés: OUI
- Instantanéité: NON

## Solutions identifiées

1. Les consommateurs sont des API Rest déclenchées via webhook par Gitlab lorsqu'il y a des contributions aux équipes (push)
   - **Avantages** 
     - Consommateurs: simple de mise en oeuvre (développement d'une API Rest + ajout dans Gitlab d'un webhook par consommateur)
   - **Inconvénients**
     - Chaque consommateur doit lire le contenu du repo Gitlab des équipes 
     - Chaque consommateur est en charge soit d'identifier les modifications depuis la dernière exécution, soit de rejouer les actions sur toutes les équipes
     - Mauvaise tolérance à la panne (dépendant du webhook Gitlab) 

   ```mermaid
   graph TD
       subgraph Gitlab
       RepoEquipe
       end
       RepoEquipe -->|Webhook| CreationEquipeGitlab(CreationEquipeGitlab)
       RepoEquipe -->|Webhook| CreationEquipeConcourse(CreationEquipeConcourse)
       RepoEquipe -->|Webhook| CreationAutre(Etc.)
       CreationEquipeGitlab-.->|lire| RepoEquipe
       CreationEquipeConcourse-.->|lire| RepoEquipe
       CreationAutre-.->|lire| RepoEquipe
   ```

2. Les consommateurs sont des traitements (pipelines/jobs) exécutés par une CI déclenchée par un webhook Gitlab 
    - **Avantages**
        - Consommateurs: simple de mise en oeuvre (développement d'un pipeline/job + ajout d'un webhook) 
        - Par rapport à la solution 1 :
          - Pas besoin de déployer une application et de la monitorer
          - Meilleure tolérence à la panne potentielle des webhook (possibilité d'interroger avec une fréquence définie les contributions dans le repo) 
          - Lecture des données des équipes dans le repo git facilitée (par la CI)
    - **Inconvénients**
        - Problématique d'industrialisation des jobs (s'assurer que le code du job qui réalise le traitement a été testé)
        - La CI qui héberge les jobs devient la plateforme d'exécution 
        - Chaque consommateur est en charge soit d'identifier les modifications depuis la dernière exécution, soit de rejouer les actions sur toutes les équipes

    ```mermaid
    graph TD
        subgraph TMJ-equipes
        Gitlab[Repo equipes] 
        end
        
        subgraph CI: concourse, etc
        Gitlab[Repo equipes] -->|Webhook| CreationEquipeGitlab(JobCreationEquipeGitlab)
        Gitlab[Repo equipes] -->|Webhook| CreationEquipeConcourse(JobCreationEquipeConcourse)
        Gitlab[Repo equipes] -->|Webhook| CreationAutre(Etc.)
        end
    ```

3. Utilisation d'un producteur de messages (déclenché par webhook Gitlab) qui envoie dans une file de messages (les consommateurs s'abonnent à la file de message) 
  - **Avantages** 
    - Les types de contributions (création équipe, modification équipe, etc.) peuvent etre analysées à 1 seul endroit et des messages typés peuvent etre envoyés aux consommateurs
    - Bonne tolérance à la panne entre le producteur de message et les consommateurs (système dédié) 
  - **Inconvenients** 
    - Complexité de mise en oeuvre : création d'un producteur de messages qui est appelé par le webhook Gitlab + déploiement/administration d'un broker de messages
    - Complexité du développement d'une souscription à une file de messages vs développement d'un endpoint d'api rest ?
    - Mauvaise tolérance à la panne potentielle de webhooks

  ```mermaid  
  graph LR    
      subgraph TMJ-equipes
      Gitlab(Repo equipes)
      end
      Gitlab -->|Webhook| JobConcourse(Job concourse MaJ configMap)    
      subgraph concourse
      JobConcourse
      end
      JobConcourse -.->|update donnees equipes| Api(API + Producteur de message)
      Api -.->|cron refresh equipes| Api
      Api -->|message| FileDeMessage{{FileDeMessage}}
      FileDeMessage <--> CreationEquipeGitlab
      FileDeMessage <--> CreationEquipeConcourse
      FileDeMessage <--> CreationAutre[Etc.]
  ```
   
  Choix de la file de messsages qui répond aux [contraintes](#Critères de choix de la solution:) entre ces 2 outils:
  - rabbitMQ (TAS)
  - Kafka

  | Fonctionnalité                               | RabbitMq                                                                        | Kafka                                                                                                                                          |
  |:---------------------------------------------|:--------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------|
  | ordre des messages respectés                 | **NON**                                                                         | **OUI** (si 1 seule partition dans le topic)                                                                                                   |
  | tous les abonnés peuvent avoir le message | **NON ?** (consommé = dépilé de la file). Il faudrait une file par consommateur | **OUI** (pour ceux qui ne sont pas arrivé à l'expiration paramétrée). Possibilité de demander tous les messages encore conservés dans le topic |


  **Décicion sur le choix de la file de message :**
    
  Rabbit semble plus adapté lorsqu'il y a du rooting complexe de messages à faire avec 1 consommateur par file (https://youtu.be/GMmRtSFQ5Z0?t=638) (smart-broker & dumb-consumer )
  Alors que Kafka semble plus adapté à des multiples consommateurs (https://youtu.be/GMmRtSFQ5Z0?t=725) (dumb-broker & smart-consumer )

  Sources:
  - https://www.youtube.com/watch?v=GMmRtSFQ5Z0
  - https://www.simplilearn.com/kafka-vs-rabbitmq-article#:~:text=Deciding%20Between%20Kafka%20and%20RabbitMQ&text=While%20Kafka%20is%20best%20suited,for%20both%20Kafka%20and%20RabbitMQ
  - https://stiller.blog/2020/02/rabbitmq-vs-kafka-an-architects-dilemma-part-2/#:~:text=Message%20Ordering&text=The%20RabbitMQ%20documentation%20states%20the,order%20that%20they%20were%20sent

4. Utilisation d'un webhook personnalisé pour chaque client et déclenché à chaque modification par le composant de gestion du catalogue OI185.

   Le repo des équipes contient la liste des équipes et la liste des webhooks des clients à notifier en cas de modification.
   Chaque modification d'un des deux "référentiels" entraine la remise à jour des ConfigMap correspondant dans K8S via un job Concourse.

   Un `schedule` scrute les modifications au niveau des équipes.
   Chaque modification provoque un appel à l'API de notification.
   Cette ressource va invoquer le webhook correspondant de chacun des clients pour chaque notification détectée.

    ```mermaid  
    graph LR    
        subgraph TMJ-equipes
        Equipes(Repo equipes)
        Clients(Repo clients)
        end
        subgraph Concourse
        JobEquipes(Job MaJ équipes)
        JobClients(Job MaJ clients)
        end
        subgraph OI185
        ConfigMapEquipes(Equipes)
        ConfigMapClients(Clients)
        RefreshEquipes(RefreshEquipes - scheduler)
        ApiNotif(API notification)
        end
        subgraph EquipeGitlab
        GestionRunners(Gestion runners)
        GestionGroupes(Gestion groupes)
        end
        subgraph EquipeConcourse
        GestionWorkers(Gestion workers)
        end

        Equipes -->|Webhook| JobEquipes
        Clients -->|Webhook| JobClients
        JobEquipes -->|MaJ ConfigMap| ConfigMapEquipes
        JobClients -->|MaJ configMap| ConfigMapClients
        RefreshEquipes -->|1 - Recherche MaJ| ConfigMapEquipes
        RefreshEquipes -->|2 - Lance notif| ApiNotif
        ApiNotif -->|Webhook| GestionRunners
        ApiNotif -->|Webhook| GestionGroupes
        ApiNotif -->|Webhook| GestionWorkers
    ```

   **Avantages**
    - Architecture simple à mettre en place.
    - Pas de composant supplémentaire à gérer.
    - Chaque type de modification peut invoquer un webhook différent en fonction des besoins des clients.

   **Inconvenients**
    - Mauvaise tolérance à la panne potentielle des clients des webhooks finaux (mécanisme de retry à prévoir à terme)
    - Nécessite de garder en mémoire/cache l'état des équipes au moment du dernier envoi. Besoin de pouvoir retrouver cet état même en cas de redémarrage des pods de l'OI185.
    - Ne permet pas de scalabilité : car stockage de l'état des équipes en mémoire du pod (à terme il faudra revoir le système de stockage et rafraichissement des équipes)

## Décision:

**La solution 4 (émission de requête Http à chaque évènement) a été retenue.**

**Critères de choix:**
  - Meilleure interopérabilité (requêtes Http) afin de déclencher des traitements dans une API, un job d'une CI, etc.
  - Moins coûteux à maintenir (pas de file de message à déployer, paramétrer et monitorer)

Abonnement des clients/consommateurs:
  - l'abonnement des clients/consommateurs se fera par contribution dans un repo/fichier d'abonnement
  - 1 abonnement (=1 endpoint) par type d'évènement
  - le payload sera fourni dans le body de la requête

Types d'évènements émis:
  - Creation d'équipe
  - Suppression d'équipe
  - Ajout membre(s) dans une équipe
  - Suppression membre(s) dans une équipe
  - Modification d'équipe (tags, perimetre) 

Rechargement de la liste des clients/consommateurs dans l'OI185 :
  - Lecture du fichier des clients à notifier à chaque évènement émis

**Points de vigilences:**
  - Ne permet pas de scalabilité (car stockage de l'état des équipes en mémoire du pod) : à terme il faudra revoir le système de stockage et rafraichissement des équipes


