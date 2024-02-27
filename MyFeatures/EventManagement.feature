Feature: Event Management

  Scenario: Organizer creates a new event
    Given I am the admin
    When I create a new event
    Then the event details should be saved in the system

  Scenario: Organizer edits an existing event
    Given the organizer is logged in
    And there is an existing event
    When the organizer edits the event details
    Then the changes should be reflected in the system

  Scenario: Organizer deletes an existing event
    Given the organizer is logged in
    And there is an existing event
    When the organizer deletes the event
    Then the event should be removed from the system

  Scenario: Organizer manages attendee list for an event
    Given the organizer is logged in
    And there is an existing event with attendees
    When the organizer manages the guest list
    Then the attendee count should be updated in the system

  Scenario: Organizer creates an event with specific details
    Given the organizer is logged in
    When the organizer creates a new event with details
      | Date       | Time     | Location   | Theme      | Description           | Attendee Count |
      | 2024-03-15 | 3:00 PM  | Park Plaza | Birthday   | Celebrating John's 30th | 50             |
    Then the event details should be saved in the system

  Scenario: Organizer creates a workshop event
    Given the organizer is logged in
    When the organizer creates a new workshop event
      | Date       | Time      | Location       | Theme    | Description              | Attendee Count |
      | 2024-04-10 | 10:00 AM  | Tech Hub Room  | Coding   | Introduction to Python   | 30             |
    Then the workshop event details should be saved in the system

