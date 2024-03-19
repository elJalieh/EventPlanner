Feature: Budgeting and Finance

@VIEW_BUDGET_REPORT
Scenario: View Budget Report for an Event
  Given I am an organizer4
  When I view the budget report for an event
  Then the system should display a report with all the venues and the vendors associated with the event and the remaining budget

Scenario: Don't have enough budget for venue
  Given I am an organizer4
  And I don't have a sufficient budget for venue
  When I try to assign a venue
  Then the venue won't be associated with the event

Scenario: Don't have enough budget for vendor
  Given I am an organizer4
  And I don't have a sufficient budget for vendor
  When I try to assign a vendor
  Then the vendor won't be associated with the event

  Scenario: I have enough budget
    Given I am an organizer4
    And I have sufficient budget for vendor
    And I have sufficient budget for venue
    Then the venue and vendor will be associated with the event
