## springdoc-openapi

### Description

Fournit une documentation en ligne de l'API au format OpenAPI V3 en JSON ou en YAML. 

### Site / docs complémentaires

[Article Baeldung](https://www.baeldung.com/spring-rest-openapi-documentation)

### Intégration

Il suffit d'ajouter une dépendance dans le pom.xml :

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.4</version>
</dependency>
```

### Commentaires

- Doc disponible uniquement au runtime
- Ajoute une ressource `v3/api-docs`
- Annotations de documentation différentes de celles de Springfox. La migration n'est pas transparente.
