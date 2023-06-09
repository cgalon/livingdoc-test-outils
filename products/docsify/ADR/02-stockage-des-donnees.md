---
title: Persistance des données du catalogue
type: adr
date:  2022-09-28
---
## Contexte

Besoin de décider de la stratégie de stockage des données du catalogue des équipes.

## Décision

Le lieu de stockage choisi pour la V1 est un repo GitLab (https://git-scm.pole-emploi.intra/tmj/catalogue-equipes).  
L'ensemble des équipes est stocké dans le dossier "equipes".  
Chaque équipe est définie dans un fichier YAML portant son nom.  
