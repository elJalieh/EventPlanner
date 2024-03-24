Feature:  Sign up

  Scenario Outline:trying to signing up with an existing email
    Given that the user is not logged in
    When the information exists, the email is "<Email>"
    Then signing up fails

    Examples:
      | Email                      |
      | a2y2m2a2n@gmail.com        |
      | mo.matar123@gmail.com      |
      | alaraid2003@gmail.com      |


  Scenario:trying to signing up with incorrect email format
    Given that the user is not logged in
    When the email format is incorrect
    Then signing up fails


  Scenario Outline:trying to signing up with new account
    Given that the user is not logged in
    When the information exists, the email is not "<Email>"
    Then signing up succeeds

    Examples:
      | Email                      |
      | m7mdMa234523@gmail.com     |
      | AlAA234521334521@gmail.com |
      | a3y3m3a3n@gmail.com        |