Feature: User Login

  Scenario: User logs in successfully
    Given the user is on the login page
    When the user enters valid credentials
    And clicks on the login button
    Then the user is logged in successfully

  Scenario: User fails to log in with invalid credentials
    Given the user is on the login page
    When the user enters invalid credentials
    And clicks on the login button
    Then an error message is displayed
