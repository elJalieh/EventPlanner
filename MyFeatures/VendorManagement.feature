Feature: Service Provider (Vendor) Management

  @SEARCH2
  Scenario: Search and Filter Vendors
    Given I am an organizer3
    When I search and filter vendors for the following criteria:
      |Location    | Availability | Pricing | Reviews |
      |City Center | Available    | 500     | 4.5     |
    Then the list of vendors should be displayed

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