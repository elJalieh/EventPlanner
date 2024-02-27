Feature:Login

  Scenario Outline: Valid information
    Given that the user is not logged in
    When the information is valid email is "<Email>" and password is "<Password>"
    Then user successfully log in

    Examples:
      | Email                      | Password |
      | a2y2m2a2n@gmail.com        | 123      |
      | mo.matar123@gmail.com      | 123     |
      | alaraid2003@gmail.com      | 123    |

  Scenario Outline: Invalid email
    Given that the user is not logged in
    When the email is invalid email is "<Email>" and password is "<Password>"
    Then user failed in log in

    Examples:
      | Email                       | Password |
      | a2y2m2sdfgsda2n@gmail.com   | 123      |
      | mo.matasdfgr123@gmail.com   | 123     |
      | alaraid20sdfg03@gmail.com   | 123    |

  Scenario Outline: Invalid password
    Given that the user is not logged in
    When the password is invalid email is "<Email>" and password is "<Password>"
    Then user failed in log in

    Examples:
      | Email                       | Password |
      | a2y2m2a2n@gmail.com         | 12311    |
      | mo.matar123@gmail.com       | 123421   |
      | alaraid2003@gmail.com       | 1234521  |


  Scenario Outline: Invalid information
    Given that the user is not logged in
    When the information are invalid email is "<Email>" and password is "<Password>"
    Then user failed in log in
    Examples:
      | Email                       | Password |
      | a2y2msdf2a2n@gmail.com      | 12sdf3   |
      | mo.matasadfr123@gmail.com   | 12sdf34  |
      |                             |          |