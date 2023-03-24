## git-changelog-maven-plugin

### Description

Permet de générer un changelog à partir des commits Git.

### Site / docs complémentaires

[Sources](https://github.com/tomasbjerre/git-changelog-maven-plugin)

### Intégration

```xml
<plugin>
    <groupId>se.bjurr.gitchangelog</groupId>
    <artifactId>git-changelog-maven-plugin</artifactId>
    <version>1.44</version>
</plugin>
```

### Commentaires

- Utilise un template `mustache` permettant de générer le changelog dans plusieurs langages.
- Le rapport peut facilement être customisé.
- Pas d'accès direct à l'historique de Git.
