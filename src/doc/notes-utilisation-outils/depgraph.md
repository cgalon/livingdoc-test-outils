## Depgraph

### Description

Plugin Maven de génération d'un graphe de dépendances.

### Site / docs complémentaires

[Site](https://github.com/ferstl/depgraph-maven-plugin)

### Intégration

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

### Commentaires

- Génère un graphe de dépendances Maven.
- Plusieurs formats en sortie : dot, json, text...
- Nécessite un composant d'affichage du fichier de résultat.

