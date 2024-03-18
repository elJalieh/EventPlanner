Feature: Service Provider (Vendor) Management


  Scenario: Search and Filter Vendors by location
    Given I am an organizer3
    When I search for the vendor by location "salfeet"
    Then the list of vendors for the location appears.

  Scenario: Search and Filter Vendors by pricing
    Given I am an organizer3
    When I search for the vendor by the price 1000
    Then the list of vendors for the price appears.

  Scenario: Search and Filter Vendors by availability
    Given I am an organizer3
    When I search for the vendor by availability true
    Then the list of vendors for the availability appears.

  Scenario: Search and Filter Vendors reviews
    Given I am an organizer3
    When I search for the vendor by the review 4
    Then the list of vendors for that review appears.


  @REQUEST_PACKAGE_AND_NEGOTIATE
  Scenario: Request a Package from a Vendor
    Given I am an organizer3
    When I negotiate the contract terms with the vendor
    And I accept the contract
    And I have booked the vendor
    And I request a package 2 from the vendor for an event
    Then the vendor with his packets will be booked by me

#  @NEGOTIATE_CONTRACT
#  Scenario: Negotiate Contract with a Vendor
#    Given I am an organizer3
#    And I accept the contract
#    Then the vendor should be booked by me

#  @BOOK_VENDOR
#  Scenario: Manage Vendor Bookings
#    Given I am an organizer3
#    When I book the vendor "Elegant Catering" for the event "Birthday Celebration Party"
#    Then the vendor should be marked as booked for the specified event in the system