Feature: User Form Automation

  As a QA engineer
  I want to fill the user form automatically
  So that I can test form submission with different data

  Background:
    # Navigate to the form page before every scenario
    Given I open the browser "<browser>"

  @SmokeTest
  Scenario Outline: Fill user form and submit in different browsers
    # Open the browser dynamically based on the Example row
    And  I navigate to the form page "https://testing.qaautomationlabs.com/form.php"
    When I fill the form with following details
      | firstName  | middleName | lastName | email               | password  | address       | city        | state      | pinCode |
      | <firstName> | <middleName> | <lastName> | <email> | <password> | <address> | <city> | <state> | <pinCode> |
    And I submit the form
    Then I should see the form submitted successfully

    Examples:
      | browser  | firstName | middleName | lastName | email                  | password  | address       | city        | state      | pinCode |
      | Firefox  | John      | Middle     | Doe      | john.doe@example.com   | Test@1234 | 123 Main St   | Los Angeles | California | 90001   |
      | Chrome   | Jane      | A.         | Smith    | jane.smith@example.com | Pass@123  | 456 Elm St    | San Diego   | California | 92101   |
