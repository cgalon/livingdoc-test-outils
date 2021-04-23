Feature: Gestion de la collection
  On doit pouvoir effectuer des opérations d'ajout ou de suppression sur la collection

  Scenario: Ajout dans la collection
    Given une collection de 3 comics
    When je supprime un comics
    Then je dois avoir une collection avec 2 comics
