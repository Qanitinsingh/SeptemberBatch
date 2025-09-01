Feature: User Form Automation
  As a QA engineer
  I want to fill the user form automatically
  So that I can test form submission with different data

  @SmokeTest
  Scenario Outline: Fill user form and submit
    Given I navigate to the form page "https://testing.qaautomationlabs.com/form.php"
    When I fill the form with following details
      | firstName  | middleName | lastName | email               | password  | address       | city        | state      | pinCode |
      | <firstName> | <middleName> | <lastName> | <email> | <password> | <address> | <city> | <state> | <pinCode> |
    And I submit the form
    Then I should see the form submitted successfully

    Examples:
      | firstName | middleName | lastName | email                 | password  | address       | city        | state      | pinCode |
      | John      | Middle     | Doe      | john.doe@example.com  | Test@1234 | 123 Main St   | Los Angeles | California | 90001   |
