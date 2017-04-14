# Living Documentation

Projet de tests divers et vériés sur la génération de documentation à partir du code.

## Utilisation

- `npm run nettoie` => Supprime tous les répertoires de génération (exécutables ou documentation).
- `npm run construit` => `nettoie` et créé les exécutables.
- `npm run lance-dev` => `nettoie`, construit et lance le jar SpringBoot.
- `npm run genere-la-doc` => Génére la documentation du projet.
- `npm run passe-a-la-version-suivante` => Créé un tag de la version courante, augmente le 3eme digit de la version et effectue un commit
 avec ce nouveau numéro de version.

## Le code du projet

Projet Springboot ultra simple servant de support à la génération de la doc.

URLs de test : 
- [http://localhost:8080/personne/random]
- [http://localhost:8080/personne?id=2]



## Les outils de génération de doc testés

### Plugin de génération de changelog Git

```xml
<groupId>se.bjurr.gitchangelog</groupId>
<artifactId>git-changelog-maven-plugin</artifactId>
<version>1.44</version>
```
Permet de générer un changelog en Markdown du projet

