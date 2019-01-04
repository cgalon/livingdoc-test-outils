# Living Documentation

Projet de test sur la génération de documentation à partir du code.


## Utilisation

- `npm run nettoie` => Supprime tous les répertoires de génération (exécutables ou documentation).
- `npm run construit` => `nettoie` et créé les exécutables.
- `npm run lance-dev` => `nettoie`, construit et lance le jar SpringBoot.
- `npm run genere-la-doc` => Génère la documentation du projet.
- `npm run genere-les-releases-notes` => Lance le script de génération de la release note 

## Le code source

Projet Springboot ultra simple servant de support à la génération de la doc.

URLs de test : 
- [http://localhost:8080/personne/random]
- [http://localhost:8080/personne?id=2]


## Génération de la documentation

Tous les outils testés (cf. chapitre ci-dessous) génèrent leurs docs dans le répertoire `target/generated-docs`.



## TODO liste

### Ce qui serait à tester

- `En cours` Génération d'un site de documentation à partir de fichiers Asciidoctor avec Antora (https://antora.org/)
- `Bloqué` Génération d'un changelog en asciidoc avec un module NPM
- `Bloqué` Génération d'un changelog ne prenant en compte que les messages au format attendu
- Doc d'API des services REST générée avec API-Console CLI (https://github.com/mulesoft/api-console/blob/master/docs/build-tools.md)
- osprey-mock-service pour générer des bouchons à partir du fichier RAML
- abao pour vérifier que l'implémentation des ressources correspond à la spec du fichier RAML
- Plugin de test des liens dans les fichiers générés par Asciidoctor
- Assemblage des différentes docs dans un seul index.html
- Génération d'un glossaire à partir d'annotations spécifiques
- Génération de doc à partir de tests
  - Avec Serenity
  - Avec le plugin spring-restdocs-mockmvc
- Doc d'API des services REST générée par Swagger

### Ressources

Conférence sur RAML (DEVOXX) : https://www.youtube.com/watch?v=4oLUXZXUZYc

### DONE

Ce qui a été fait :
- Tester les plugins de l'archétype "living doc" du github du même nom (Benoit Prioux) (glossary / diagram / wordcloud)
- Doc d'API des services REST générée à partir du RAML
- Génération d'un changelog en asciidoc avec un plugin Maven


## Les outils de génération de doc testés

### Génération de changelog via un package npm

[Repo NPM](https://www.npmjs.com/package/gitlog)

[Source](https://github.com/domharrington/node-gitlog)

**_En cours_** 


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
**_Verdict :_** Rendu très sobre. Utilise la javadoc pour la définition.



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

**_Verdict :_** Fonctionne avec la lib viz.js. Ne prend pas en 
compte toutes les dépendances d'une classe vers d'autres classes (seuls les attributs d'instance ont l'air d'être pris en compte).  
L'idée parait intéressante mais il faudrait améliorer le plugin. 
