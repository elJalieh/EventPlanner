Feature: Service Provider

  Scenario: Adding New Package
    Given I am Service Provider
    When I choose to add a new package to my list
    Then the package should be added to the list

  Scenario: Editing Package
    Given I am Service Provider
    When I choose to edit an existing package
    Then the package should be updated in the list

  Scenario: Delete a Package
    Given I am Service Provider
    When I choose to delete an existing package
    Then the package should be deleted from the list

  Scenario: Edit Contract
    Given I am Service Provider
    When I decide to make changes to my contract
    Then a new contract should be created

  Scenario: Show Packages
    Given I am Service Provider
    When I choose to view my list of packages
    Then a list for my packages will be displayed

  Scenario: My Customer
    Given I am Service Provider
    When I choose to view my customer details
    Then the customer details should be displayed if there's an active deal

  Scenario: My Customer
    Given I am Service Provider
    When I choose to view my customer details
    Then the customer details should not be displayed if there's no active deal