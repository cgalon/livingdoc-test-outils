## Swagger

### Description

Permet de générer une documentation de ressources REST au format OpenAPI.
Ici utilisé au travers de SpringFox.

**Désactivé suite au passage en OpenAPI V3 avec Springdoc-openapi**

### Site / docs complémentaires

[Site](https://swagger.io/tools/swagger-ui/)

[SpringFox](https://springfox.github.io/springfox/)

[Pour débuter](https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/)

### Intégration

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

### Commentaires

- Nécessite l'ajout d'annotations spécifiques à Swagger.
- Nécessite que l'application fonctionne.
- Génère une documentation très claire et très agréable à consulter.
- La documentation produite permet de tester les ressources.
- Ne produit que de la documentation de ressources REST, pas documentation "métier".
- Implémentation du standard OpenAPI V2.
- L'utilisation de Springfox est incompatible avec l'utilisation de springdoc-openapi-ui pour avoir une doc openAPI V3
- Springfox n'est plus maintenu
