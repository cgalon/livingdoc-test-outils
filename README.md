# Living Documentation

Projet de tests divers et variés sur la génération de documentation à partir du code.

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



## La liste des courses

### TODO
Ce qui est à faire :
- Test des liens dans les fichiers générés par Asciidoctor 
- Génération de doc Asciidoc avec l'image Docker de Asciidoctor et pas le plugin Maven
- Doc d'API des services REST générée par Swagger
- Doc d'API des services REST générée avec API-Console CLI (https://github.com/mulesoft/api-console/blob/master/docs/build-tools.md)
- Génération d'un changelog en asciidoc avec un module NPM
- Assemblage des différentes docs dans un seul index.html
- Génération d'un glossaire à partir d'annotations spécifiques
- Génération d'un changelog ne prenant en compte que les messages au format attendu
- Génération de doc à partir de tests
  - Avec Serenity
  - Avec le plugin spring-restdocs-mockmvc


### DONE

Ce qui a été fait :
- Tester les plugins de l'archétype "living doc" du github du même nom (Benoit Prioux) (glossary / diagram / wordcloud)
- Doc d'API des services REST générée à partir du RAML
- Génération d'un changelog en asciidoc avec Maven



## Les outils de génération de doc testés

### Plugin Maven de génération de changelog Git

Permet de générer un changelog en Markdown du projet

[Sources](https://github.com/tomasbjerre/git-changelog-maven-plugin)

```xml
<groupId>se.bjurr.gitchangelog</groupId>
<artifactId>git-changelog-maven-plugin</artifactId>
<version>1.44</version>
```
**_Verdict :_** Le markdown généré est propre. A essayer sur nos projets qui ont beaucoup plus de commits.


### Plugin Maven de génération de glossaire

Permet de générer de la doc à partir d'une annotation @Glossaire

[Sources](https://github.com/LivingDocumentation/livingdoc-maven-plugin)

```xml
<groupId>io.github.livingdocumentation</groupId>
<artifactId>livingdoc-maven-plugin</artifactId>
<version>0.3</version>
...
<goals>
    <goal>glossary</goal>
</goals>
```
**_Verdict :_** Rendu pas terrible. Utilise la javadoc pour la définition.



### Plugin de génération de doc d'API à partir de RAML

[Sources](https://github.com/raml2html/raml2html)

```text
raml2html livingdoc.raml > livingdoc-api.html
```

**_Verdict :_** 
Rendu sympa sur une seule page HTML.



### Plugin Maven de génération d'un nuage de mots

Permet de générer un nuage mots à partir du code source

[Sources](https://github.com/LivingDocumentation/livingdoc-maven-plugin)

```xml
<groupId>io.github.livingdocumentation</groupId>
<artifactId>livingdoc-maven-plugin</artifactId>
<version>0.3</version>
...
<goals>
    <goal>wordcloud</goal>
</goals>
```

**_Verdict :_** Rendu assez joli mais pas très utile.



### Plugin Maven de génération d'un diagramme basé sur l'architecture hexagonale

Permet de générer un diagramme basé sur l'architecture hexagonale pour aider à vérifier la conception du domaine. 

[Sources](https://github.com/LivingDocumentation/livingdoc-maven-plugin)

```xml
<groupId>io.github.livingdocumentation</groupId>
<artifactId>livingdoc-maven-plugin</artifactId>
<version>0.3</version>
...
<goals>
    <goal>diagram</goal>
</goals>
```

**_Verdict :_** Fonctionne avec la lib viz.js. Ne prend pas en compte toutes les dépendances d'un classe vers d'autres classes (seuls les
 attributs d'instance ont l'air d'être pris en compte). L'idée parait intéressante mais il faudrait améliorer le plugin. 

