# Living Documentation

Projet de test sur la génération de documentation à partir du code.

## Utilisation

- `npm run nettoie` => Supprime tous les répertoires de génération (exécutables ou documentation).
- `npm run construit` => `nettoie` et créé les exécutables.
- `npm run lance-dev` => `nettoie`, construit et lance le jar SpringBoot.
- `npm run genere-la-doc` => Génère la documentation du projet.

## L'application de support

Il s'agit d'une application de gestion d'une collection de Comics. Elle est conçue en DDD.  
L'application n'est pas fonctionnelle. Il manque des ressources REST, de la persistance... Néanmoins il y a suffisamment de code pour tester les différents outils de génération de documentation ci-dessous.  

L'application comprend un seul domaine et 3 "bounded context" :  
- Le référentiel des comics existants.  
- La collection des comics possédés par l'utilisateur.  
- La liste des comics manquants.  

La liste d'achat (comics manquants) est générée par delta entre les séries définies dans le référentiel et la liste des comics déjà acquis par l'utilisateur.

L'application est couverte par des tests unitaires et utilise l'architecture hexagonale pour chacun de ses contextes bornés.

## Backlog

[Lien public vers la backlog](http://kanboard.dckxm011.sii24.pole-emploi.intra/public/board/1acc2f5ea04b759e662e8fb3f42bc697f1c637fe1ee7fb124289796efd1e)

## Génération de la documentation

Tous les outils testés (cf. chapitre ci-dessous) génèrent leurs docs dans le répertoire `target/generated-docs`. Ils sont tous référencés dans la page `index.html`.

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

**NB :** Dans le projet actuel, il y a un bug avec la génération de diagramme avec Ditaa. En cours d'investigation.

**_Commentaires :_**

L'exemple de génération de document (HTML et PDF) utilisé dans ce projet est basé sur 2 articles sur Asciidoctor écrits en 2016 par l'Incubateur. Ils contiennent tout ce qu'il y a à savoir sur Asciidoctor.  

### Génération de changelog via une lib JS

Permet de générer un changelog à partir des commits Git de un ou plusieurs projets, en JS

[Repo NPM](https://www.npmjs.com/package/gitlog)  
[Source](https://github.com/domharrington/node-gitlog)  

**_Commentaires :_**

- Accès direct aux données des commits.  
- Permet de manipuler des objets JS.  
- Permet de générer le changelog au format que l'on veut.  
- Fonctionne très bien.  

### Plugin Maven de génération de changelog Git

Permet de générer un changelog à partir des commits Git.

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
- Pas d'accès direct à l'historique de Git.

### Plugin Maven de LivingDoc

Plugin Maven "multi-usage" proposant des outils de génération de documentation à partir du code.

**NB :** Les 3 outils suivants nécessitent la récupération de ressources spécifiques afin que le rendu des résultats fonctionne correctement.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-remote-resources-plugin</artifactId>
    <version>1.5</version>
    <configuration>
        <resourceBundles>
            <resourceBundle>io.github.livingdocumentation:shared-resources:0.3</resourceBundle>
        </resourceBundles>
        <outputDirectory>target/generated-docs</outputDirectory>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>process</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

#### Génération de glossaire

Permet de générer de la doc à partir d'une annotation @Glossary

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

#### Génération d'un nuage de mots

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

#### Génération d'un diagramme basé sur l'architecture hexagonale

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
- Le visuel n'est pas parfait mais il permet de vérifier certaines dépendances (et le sens de ces dépendances) entre les objets.
- L'idée parait intéressante mais il faudrait améliorer le plugin.

### Plugin de génération de doc d'API à partir de RAML

[Sources](https://github.com/raml2html/raml2html)

```text
raml2html livingdoc.raml > livingdoc-api.html
```

**_Commentaires :_**  

- Rendu sympa sur une seule page HTML.

