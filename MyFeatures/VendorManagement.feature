Feature: Service Provider (Vendor) Management

  @ADD3
  Scenario: Add a New Vendor to the Database
    Given I am an admin
    When I add a new vendor to the database with the following details:
      | Vendor Name      | Category    | Services        | Location      | Availability | Pricing  | Reviews |
      | Elegant Catering | Catering    | Food, Beverage  | City Center    | Available    | $1000    | 4.5     |
    Then the new vendor "Elegant Catering" should be added to the vendor database

  @SEARCH2
  Scenario: Search and Filter Vendors for a Venue
    Given I am an organizer3
    When I search and filter vendors for the venue "Grand Banquet Hall" with the following criteria:
      | Category   | Location    | Availability | Pricing | Reviews |
      | Catering   | City Center | Available    | $500   | 4        |
    Then the list of vendors should be displayed based on the specified criteria for the venue "Grand Banquet Hall"

  @REQUEST_PACKAGE
  Scenario: Request a Package from a Vendor
    Given I am an organizer3
    When I request a package from the vendor "Elegant Catering" for the event "Birthday Celebration Party"
    Then the vendor should receive a package request notification

  @NEGOTIATE_CONTRACT
  Scenario: Negotiate Contract with a Vendor
    Given I am an organizer3
    When I negotiate the contract terms with the vendor "Elegant Catering" for the event "Birthday Celebration Party"
    Then the contract terms should be updated in the system, and the vendor should receive a notification

  @BOOK_VENDOR
  Scenario: Manage Vendor Bookings
    Given I am an organizer3
    When I book the vendor "Elegant Catering" for the event "Birthday Celebration Party"
    Then the vendor should be marked as booked for the specified event in the system
