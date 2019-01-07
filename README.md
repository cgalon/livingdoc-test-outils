# Living Documentation

Projet de test sur la génération de documentation à partir du code.

## Utilisation

- `npm run nettoie` => Supprime tous les répertoires de génération (exécutables ou documentation).
- `npm run construit` => `nettoie` et créé les exécutables.
- `npm run lance-dev` => `nettoie`, construit et lance le jar SpringBoot.
- `npm run genere-la-doc` => Génère la documentation du projet.

## L'application de support

Projet Springboot ultra simple servant de support à la génération de la doc.

URLs de test :

- [http://localhost:8080/personne/random]
- [http://localhost:8080/personne?id=2]

### Réécriture application support

L'application support actuelle est vraiment trop simpliste pour être représentative d'un domaine fonctionnel.  
La proposition est de prendre un nouveau sujet d'exemple pour le réécrire en DDD avec de l'Event Sourcing.

**Notes :**

- Proposition de sujet : Gestion d'une collection de BDs.
- Plusieurs Bounded Contexts
  - Collection de mangas
  - Collections de comics
  - Collection d'albums classiques
  - Wish List
- Ecriture en DDD
- Ecriture en TDD
- Implémenter des TI
- Utiliser une architecture hexagonale
- Utilisation de CQRS/ES

## Backlog

[Lien public vers la backlog](http://kanboard.dckxm011.sii24.pole-emploi.intra/public/board/1acc2f5ea04b759e662e8fb3f42bc697f1c637fe1ee7fb124289796efd1e)

## Génération de la documentation

Tous les outils testés (cf. chapitre ci-dessous) génèrent leurs docs dans le répertoire `target/generated-docs`.

## Les outils de génération de doc testés

### Génération de fichiers Asciidoctor en PDF

Ecriture de docs en Ascidoctor :  

- [Source](https://asciidoctor.org/)
- [CheatSheet](https://powerman.name/doc/asciidoc)

Utilisation d'une image Docker spécifique qui embarque tous les composants nécessaires.  

```bash
docker pull asciidoctor/docker-asciidoctor
```

Utilisation d'un alias Linux :

```bash
alias genere-les-docs-de-ce-repertoire-en-pdf='docker run --rm -v $(pwd):/documents/ asciidoctor/docker-asciidoctor asciidoctor-pdf *.adoc'
```

### Génération de changelog via une lib JS

Permet de générer un changelog à partir des commits Git de un ou plusieurs projets, en JS

[Repo NPM](https://www.npmjs.com/package/gitlog)  
[Source](https://github.com/domharrington/node-gitlog)  

**_Commentaires :_** 

- Accès direct aux données des commits  
- Permet de manipuler des objets JS  
- Permet de générer le changelog au format que l'on veut  
- Fonctionne très bien  

### Plugin Maven de génération de changelog Git

Permet de générer un changelog à partir des commits Git

[Sources](https://github.com/tomasbjerre/git-changelog-maven-plugin)

```xml
<plugin>
    <groupId>se.bjurr.gitchangelog</groupId>
    <artifactId>git-changelog-maven-plugin</artifactId>
    <version>1.44</version>
</plugin>
```

**_Commentaires :_**  

- Utilise un template `mustache` permettant de générer le changelog dans plusieurs langages.  
- Le rapport peut facilement être customisé.  

### Plugin Maven de génération de glossaire

Permet de générer de la doc à partir d'une annotation @Glossaire

[Sources](https://github.com/LivingDocumentation/livingdoc-maven-plugin)

```xml
<plugin>
    <groupId>io.github.livingdocumentation</groupId>
    <artifactId>livingdoc-maven-plugin</artifactId>
    <version>0.3</version>
    ...
    <goals>
        <goal>glossary</goal>
    </goals>
</plugin>
```

**_Commentaires :_**

- Rendu très sobre.
- Utilise la javadoc pour la définition.  
- Génère du .adoc et du .html.  
- Pourrait être amélioré ou réécrit pour ajouter des paramètres, par exemple.  

### Plugin Maven de génération d'un nuage de mots

Permet de générer un nuage mots à partir du code source

[Sources](https://github.com/LivingDocumentation/livingdoc-maven-plugin)

```xml
<plugin>
    <groupId>io.github.livingdocumentation</groupId>
    <artifactId>livingdoc-maven-plugin</artifactId>
    <version>0.3</version>
    ...
    <goals>
        <goal>wordcloud</goal>
    </goals>
</plugin>
```

**_Commentaires :_**

- Rendu assez joli.  
- Surtout utilisé pour vérifier que le code "parle" bien du métier plutôt que de la technique.  

### Plugin de génération de doc d'API à partir de RAML

[Sources](https://github.com/raml2html/raml2html)

```text
raml2html livingdoc.raml > livingdoc-api.html
```

**_Commentaires :_**  
Rendu sympa sur une seule page HTML.

### Plugin Maven de génération d'un diagramme basé sur l'architecture hexagonale

Permet de générer un diagramme basé sur l'architecture hexagonale pour aider à vérifier la conception du domaine. 

[Sources](https://github.com/LivingDocumentation/livingdoc-maven-plugin)

```xml
<plugin>
    <groupId>io.github.livingdocumentation</groupId>
    <artifactId>livingdoc-maven-plugin</artifactId>
    <version>0.3</version>
    ...
    <goals>
        <goal>diagram</goal>
    </goals>
</plugin>
```

**_Commentaires :_**  

- Fonctionne avec la lib viz.js. 
- Ne prend pas en compte toutes les dépendances d'une classe vers d'autres classes (seuls les attributs d'instance ont l'air d'être pris en compte).  
- L'idée parait intéressante mais il faudrait améliorer le plugin.
