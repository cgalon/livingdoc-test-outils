## Spring RestDocs

### Description

Module de génération de rapports de test de ressources REST Spring RestDocs

Permet de générer un rapport d'exécution de tests de composants REST écrits avec Spring. Le test se fait sur la ressource en mode "TIC".

### Site / docs complémentaires

[Doc du module](https://spring.io/projects/spring-restdocs)

[Doc de démarrage](https://www.baeldung.com/spring-rest-docs)

### Intégration

```xml
<dependency>
    <groupId>org.springframework.restdocs</groupId>
    <artifactId>spring-restdocs-mockmvc</artifactId>
    <scope>test</scope>
</dependency>
```

### Commentaires

- Génère plusieurs .adoc pour chaque test (requête HTTP, requête avec curl, requête avec httpie, réponse).
- Possibilité de configurer les infos dans les fichiers en sortie.
- Mise en forme minimale.
- Besoin d'un autre fichier .adoc pour les agréger.
- Permet de faire un rapport HTML ou PDF pas mal si on passe un peu de temps pour agréger tous les fichiers en sortie.

