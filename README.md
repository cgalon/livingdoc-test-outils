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

## TODO

La liste des courses en vrac :
- Tester les plugins de l'archétype "living doc" du github du même nom (Benoit Prioux)
- Test des liens dans les fichiers générés par Asciidoctor 
- Génération de doc Asciidoc avec l'image Docker de Asciidoctor et pas le plugin Maven
- Doc des services REST générée par Swagger
- Assemblage des différentes docs dans un seul index.html
- Génération d'un glossaire à partir d'annotations spécifiques
- Génération d'un changelog en asciidoc
- Génération d'un changelog ne prenant en compte que les messages au format attendu
- Génération de doc à partir de tests
  - Avec Serenity
  - Avec le plugin spring-restdocs-mockmvc

