## ArchUnit

### Description

Framework de vérification de règles d'architecture et de nommage d'un composant.

### Site / docs complémentaires

[Site](https://www.archunit.org/)

### Intégration

```xml
<dependency>
    <groupId>com.tngtech.archunit</groupId>
    <artifactId>archunit-junit5</artifactId>
    <version>1.0.0</version>
    <scope>test</scope>
</dependency>
```

### Commentaires

- Pas un module de livingdoc au sens strict.
- Permet de vérifier que les règles d'architecture pour un composant sont respectées.
- Les tests pourraient être distribués sous forme de dépendance à intégrer dans les tests unitaires des composants d'une organisation.
