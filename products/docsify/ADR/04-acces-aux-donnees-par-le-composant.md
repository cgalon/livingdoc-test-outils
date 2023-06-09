---
title: Accès aux données du catalogue par le composant OI185
type: adr
date:  2022-09-28
---
## Contexte

Besoin de décider de la manière dont le composant OI185 va accéder aux données du catalogue des équipes.

## Décision

A chaque merge request sur le projet de données du catalogue des équipes, un pipeline (Gitlab-CI) vérifiera et validera le format du fichier modifié.   
Un autre pipeline récupèrera tous les fichiers d'équipes pour les transformer en [ConfigMap fichiers](https://kubernetes.io/docs/concepts/configuration/configmap/#using-configmaps-as-files-from-a-pod) pour K8S.  
Ces ConfigMap seront ensuite accessibles sous forme de fichiers par le composant OI185.
