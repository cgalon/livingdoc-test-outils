## ApprovalTests

### Description

Framework de test basé sur les résultats d'une API.
Permet d'enregistrer le comportement d'une API "en boite noire" puis de vérifier que les tests passent toujours suite à un refactoring.

### Site / docs complémentaires

[Site](https://approvaltests.com/)
[ApprovalTests.Java](https://github.com/approvals/ApprovalTests.Java)

### Intégration

Seulement deux dépendances à ajouter (Approval + sérialisation JSON) :
```xml
        <dependency>
            <groupId>com.approvaltests</groupId>
            <artifactId>approvaltests</artifactId>
            <version>18.6.0</version>
        </dependency>
```

### Commentaires

- Les tests sont joués par JUnit en même temps que les tests unitaires classiques.
- Fonctionnement basé sur la découverte du comportement du System Under Test
  - Le premier run d'un test produit un fichier de référence de ce qui est attendu en sortie.
  - On renomme le fichier de résultat produit pour "acter" le résultat attendu.
  - Les tests suivants vérifient le résultat obtenu avec le résultat attendu dans le fichier de référence.
- Les fichiers de référence sont produits automatiquement, mais nécessitent un renommage pour être pris en compte comme référence.
- Parait très intéressant pour tester et "acter" le comportement d'un SUT en boite noire puis pour le refactorer.
- C'est du "test after".
- En l'état, ne sert pas vraiment à documenter, mais à assurer de la non-regression pour de la maintenance ou du refactoring. 
