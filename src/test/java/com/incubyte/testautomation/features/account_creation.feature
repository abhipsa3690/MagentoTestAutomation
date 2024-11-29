Feature: Account Creation

  Scenario: User creates an account successfully
    Given the user is on the account creation page
    When the user enters valid details
    And submits the form
    Then the account is created successfully
