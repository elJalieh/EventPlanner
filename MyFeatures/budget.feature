Feature: Budgeting and Finance

@VIEW_BUDGET_REPORT
Scenario: View Budget Report for an Event
  Given I am an organizer4
  When I view the budget report for the event "Birthday Celebration Party"
  Then the system should display a comprehensive report with details on budgeted amounts, actual expenses, and variances

@SEARCH3
Scenario: Search Events or Venues by Price Range
  Given I am a user
  When I search for events or venues within the price "$3000"
  Then the system should display a list of events and venues that fall within the specified price range
