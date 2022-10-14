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

[Un convertisseur RAML <--> OAS en ligne](https://mulesoft.github.io/oas-raml-converter/)
**_Commentaires :_**  

- Rendu sympa sur une seule page HTML.
- Le format RAML dispose d'une grosse communauté et propose de nombreux outils ([RAML projects](https://raml.org/projects))

### Module de génération de rapports de test de ressources REST Spring RestDocs

Permet de générer un rapport d'exécution de tests de composants REST écrits avec Spring. Le test se fait sur la ressource en mode "TIC".

```xml
<dependency>
    <groupId>org.springframework.restdocs</groupId>
    <artifactId>spring-restdocs-mockmvc</artifactId>
    <scope>test</scope>
</dependency>
```

**_Commentaires :_**  

- Génère plusieurs .adoc pour chaque test (requête HTTP, requête avec curl, requête avec httpie, réponse).
- Possibilité de configurer les infos dans les fichiers en sortie.
- Mise en forme minimale.
- Besoin d'un autre fichier .adoc pour les agréger.
- Permet de faire un rapport HTML ou PDF pas mal si on passe un peu de temps pour agréger tous les fichiers en sortie.

### Swagger UI

Permet de générer une documentation de ressources REST.

[Site](https://swagger.io/tools/swagger-ui/)
[Pour débuter](https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/)

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.8.0</version>
    <scope>compile</scope>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.8.0</version>
    <scope>compile</scope>
</dependency>
```

**_Commentaires :_**  

- Nécessite l'ajout d'annotations spécifiques à Swagger.
- Nécessite que l'application fonctionne.
- Génère une documentation très claire et très agréable à consulter.
- La documentation produite permet de tester les ressources.
- Ne produit que de la documentation de ressources REST, pas documentation "métier".

### QDox

API donnant accès au contenu de classes Java d'un répertoire.

[Site](https://github.com/paul-hammant/qdox)

```xml
<dependency>
    <artifactId>qdox</artifactId>
    <groupId>com.thoughtworks.qdox</groupId>
    <version>2.0-M10</version>
</dependency>
```

**_Commentaires :_**

- Lit les fichiers sources du projet.
- Permet d'extraire toutes les informations d'une classe sous forme d'objets Java.
- API très simple à utiliser et très complète.
- Pas de formattage en sortie. chacun doit se débrouiller avec les informations brutes.
- Très intéressant pour générer de la doc à partir de code source.

### Linkchecker

Plugin Maven de vérification des liens HTML dans une arborescence.

[Site](http://maven.apache.org/plugins/maven-linkcheck-plugin/)

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-linkcheck-plugin</artifactId>
    <version>1.2</version>
    <configuration>
        <baseURL>${project.build.directory}/generated-docs</baseURL>
        <forceSite>false</forceSite>
    </configuration>
</plugin>
```
- Créer un rapport de test que l'on peut inclure dans un site généré par Maven.
- Quelques soucis pour vérifier les liens externes (surement dû au proxy PE, à investiguer).
- Peut être utile pour vérifier les liens d'une doc générée.

### Allure

Framework de mise en forme de rapports de test. Ici, test du plugin Maven pour les tests unitaires de Surefire.

[Site](http://allure.qatools.ru/)

```xml
<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.10.0</version>
</plugin>
```
**_Commentaires :_**

- Très belle mise en forme de rapports Surefire.
- Ne lance pas les tests lui-même mais utilise les données générées.
- Soucis de compatibilité (CORS) avec les navigateurs récents.
- Fournit un petit serveur Web intégré pour répondre au problème des CORS.

### Mermaid

Bibliothèque JS de génération graphique de diagrammes à partir écrits en texte.

[Site](https://mermaid-js.github.io/mermaid/#/) 

**_Commentaires :_**

- Très simple à utiliser (Juste une lib JS à ajouter dans une page HTML)
- Graphiques très beaux et très propres
- Syntaxe très simple à écrire ou à générer 
- Même syntaxe que pour PlantUML pour les diagrammes UML

### Anychart

Bibliothèque JS de génération graphique de diagrammes à partir de données en Javascript ou en JSON.

[Site](https://www.anychart.com/)

**_Commentaires :_**

- Très simple à utiliser (Juste une lib JS à ajouter dans une page HTML)
- Graphiques très beaux et très propres
- Beaucoup de styles de graphiques différents
- Un peu de JS à écrire
- Pourrait aussi servir pour créer des tableaux de bord
- **Attention** : La lib n'est pas gratuite

### Chart.js

Bibliothèque JS de création de graphiques à partir de données en Javascript ou en JSON.

[Site](https://www.chartjs.org/)

**_Commentaires :_**

- Très simple à utiliser (Juste une lib JS à ajouter dans une page HTML)
- Graphiques très propres
- Tous les graphiques les plus classiques
- Un peu de JS à écrire
- Pourrait aussi servir pour créer des tableaux de bord
- Gratuit

### Cucumber

Framework de test orienté BDD.

[Site](https://cucumber.io/)

**_Commentaires :_**

- Tests lancés via un plugin Maven
- Génère des rapports sobres mais propres et compréhensibles par tout le monde
- Utilise des fichiers textes pour écrire les features testées
- Les fichiers de features peuvent être repris par d'autres outils (cukedoctor, pickles...)

### Depgraph

Plugin Maven de génération d'un graphe de dépendances.

[Site](https://github.com/ferstl/depgraph-maven-plugin)

```xml
<plugin>
    <groupId>com.github.ferstl</groupId>
    <artifactId>depgraph-maven-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
        <graphFormat>dot</graphFormat>
        <outputDirectory>${project.build.directory}/generated-docs</outputDirectory>
        <showGroupIds>true</showGroupIds>
        <showVersions>true</showVersions>
    </configuration>
    <executions>
        <execution>
            <id>dependency-graph</id>
            <phase>package</phase>
            <goals>
                <goal>graph</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
- Génère un graphe de dépendances Maven.
- Plusieurs formats en sortie : dot, json, text...
- Nécessite un composant d'affichage du fichier de résultat.

### ArchUnit

Framework de vérification de règles d'architecture d'un composant.

[Site](https://www.archunit.org/)

```xml
<dependency>
    <groupId>com.tngtech.archunit</groupId>
    <artifactId>archunit-junit5</artifactId>
    <version>1.0.0</version>
    <scope>test</scope>
</dependency>
```
- Pas un module de living doc au sens strict.
- Permet de vérifier que les règles d'architecture pour un composant sont respectées.
- Peut être distribué sous forme de jar à intégrer dans les tests unitaires des composants d'une organisation.

