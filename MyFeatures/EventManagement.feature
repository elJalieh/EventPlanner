Feature: Event Management

  @ADD1
  Scenario: Create a New Event
    Given I am an organizer
    When I create a new event with the following details:
      | Date       | Time     | Location       | Theme    | Description       | Attendee Count |
      | 2024-03-15 | 19:00:00 | Grand Ballroom | Birthday | Celebration Party | 50             |
    And I manage the guest list for the event
    Then the new event "Birthday Celebration Party" should be added to the event list

  @EDIT1
  Scenario: Edit an Existing Event
    Given I am an organizer
    When I edit the event details for "Birthday Celebration Party" as follows:
      | Date       | Time     | Location   | Theme    | Description                 | Attendee Count |
      | 2024-03-15 | 20:00:00 | VIP Lounge | Birthday | Exclusive Celebration Party | 60             |
    And I manage the updated guest list for the event
    Then the event details for "Birthday Celebration Party" should be updated in the event list

  @DELETE1
  Scenario: Delete an Event
    Given I am an organizer
    When I delete the event "Birthday Celebration Party"
    Then the event "Birthday Celebration Party" should be removed from the event list