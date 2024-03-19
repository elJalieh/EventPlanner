Feature: Admin Management

  @CREATE_SP_ACCOUNT1
  Scenario: Create new account for Service Provider
    Given I logged in as an admin
    When I create an account with "<Email>", "<Password>", "<Category>", "<Location>", "<Availability>", "<Pricing>", "<Reviews>", and "<contractDescription>" as the following for a new service provider
      | Email                   | Password | Category |Location |Availability|Pricing |Reviews |contractDescription |
      | s12112161@stu.najah.edu | 123      | Singers  | Nablus  | true       |1000    |    5   |        Hi!         |
    Then I should see a success message confirming the account creation

  @DELETE_ACCOUNT1
  Scenario: Delete account
    Given I logged in as an admin
    When I delete the account with email "s12112161@stu.najah.edu"
    Then I should see a success message confirming the account deletion