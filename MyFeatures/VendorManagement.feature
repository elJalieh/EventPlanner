Feature: Service Provider (Vendor) Management

  Scenario: display all vendors
    Given I am an organizer3
    When choose to display all vendors
    Then the list of vendors appears.

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
    And I request a package 2 from the vendor for an event after displaying them
    Then the vendor with his packets will be booked by me

  @vendor_release
  Scenario: release a vendor after contracting
    Given I am an organizer3
    When I choose to release the vendor from my event
    Then The vendor will be released and my event will have no vendor set