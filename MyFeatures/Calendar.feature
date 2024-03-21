Feature: Calendar and Scheduling

  Scenario: View upcoming events and important dates
    Given I am user/organizer
    When I view the calendar
    Then I should see a list of upcoming events associated with me

#  Scenario: Schedule a task or appointment
#    Given I am user/organizer
#    When I schedule a task or appointment for "Meeting with Client" on "2024-03-25" at "10:00 AM"
#    Then I should see the task or appointment in the calendar

  Scenario: Set reminders for upcoming events and tasks
    Given I am user/organizer
    When the reminder time approaches within 2 days
    Then I should see a reminder in the reminders interface
