Feature: Calendar and Scheduling

  Scenario: View upcoming events and important dates
    Given I am user/organizer
    When I view the calendar
    Then I should see a list of upcoming events associated with me

  Scenario: Set reminders for upcoming events and tasks
    Given I am user/organizer
    When the reminder time approaches within 2 days
    Then I should see a reminder in the reminders interface
