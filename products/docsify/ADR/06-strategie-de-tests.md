---
title: Stratégie de tests
type: adr
date:  2022-10-05
---
## Contexte

Définition du périmètre de chaque niveau de test.

## Décision

TU: tests du domaine et de l'infrastructure d'acces aux données (utilisation de fichiers comme source de données, pas de configMap)
TU/TI: tests à mi-chemin entre TU et TI réalisés en QuarkusTest mais succints afin de tester en local l'API réellement déployée avec chargement de fichiers d'équipes afin d'avoir un feedback rapide 
TI: tests de tout le composant déployé sur k8s et utilisant l'API et les configMaps (la solution technique d'accès aux données choisie cf. ADR-04 complique l'exécution des TI en local) 

