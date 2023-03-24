## Allure

### Description

Framework de mise en forme de rapports de test. Ici, test du plugin Maven pour les tests unitaires de Surefire.

**_Désactivé car l'utilisation d'AspectJ pose des problèmes de compatibilité avec Spring_**

### Site / docs complémentaires

[Site](http://allure.qatools.ru/)

### Intégration

```xml
<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.10.0</version>
</plugin>
```

### Commentaires

- Très belle mise en forme de rapports Surefire.
- Ne lance pas les tests lui-même mais utilise les données générées.
- Soucis de compatibilité (CORS) avec les navigateurs récents.
- Fournit un petit serveur Web intégré pour répondre au problème des CORS.

