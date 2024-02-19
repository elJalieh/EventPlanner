Feature: User Login

  Scenario Outline: User logs in successfully with valid credentials
    Given the user accesses the sign-in command
    When the user provides valid information '<username>' , '<password>'
    Then the user should be successfully logged in
    Examples:
      | username                | password  |
      | Ayman                   | 132       |
      | s12127670@stu.najah.edu | 12345     |
      | s12127747@stu.najah.edu | 12345     |
      | asd2@gmail.com           | 1323      |

  Scenario: User login failed with valid credentials
    Given the user accesses the login command
    When the user provides valid information "Ayman" , "132" and something went wrong
    Then the system should display an error message "<error_message>"
    And the user should not be logged in


  Scenario Outline: User login with invalid credentials
    Given the user accesses the sign-in command
    When the user provides information '<username>' , '<password>'
    Then the system should display an error message "<error_message>"
    And the user should not be logged in

    Examples:
      | username       | password  | error_message       |
      | invaliduser    | invalid   | Invalid username    |
      | Ayman          | 111       | Invalid password    |
      | non@gmail.com  | 111       | User does not exist |
      |                |           | empty fields!       |