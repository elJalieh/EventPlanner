Feature: Venue Management

  @ADD2
  Scenario: Add a New Venue
    Given I am an admin
    When I add a new venue with the following details:
      | Venue Name       | Capacity | Amenities                               | Pricing     |
      | Grand Banquet Hall | 100      | Parking, Wi-Fi, Catering, AV Equipment | 5000   |
    Then the new venue "Grand Banquet Hall" should be added to the venue list

  @EDIT2
  Scenario: Edit an Existing Venue
    Given I am an admin
    When I edit the details for the venue "Grand Banquet Hall" as follows:
      | Venue Name         | Capacity | Amenities                               | Pricing     |
      | Grand Banquet Hall | 150      | Parking, Wi-Fi, Catering                | 5555   |
    Then the details for the venue "Grand Banquet Hall" should be updated in the venue list

  @DELETE2
  Scenario: Delete a Venue
    Given I am an admin
    When I delete the venue "Grand Banquet Hall"
    Then the venue "Grand Banquet Hall" should be removed from the venue list

  @LINK_VENUE_EVENT
  Scenario: Linking a Venue with an Event
    Given I am an organizer2
    When I link the venue "Grand Banquet Hall" with the event "Birthday Celebration Party"
    Then the venue "Grand Banquet Hall" should be associated with the event "Birthday Celebration Party" in the system