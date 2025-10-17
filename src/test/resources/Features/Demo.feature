Feature: Verify Login Functionality of Application


#  Scenario: Successful Login with Valid Credentials
#    Given I navigate to "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
#    When I enter the following credentials:
#      | username | password |
#      | Admin    | admin123 |
#    Then I should see the dashboard page

@SmokeTest
  Scenario: Successful Login with Valid Credentials
    Given I navigate to "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    When I enter the Username as "Admin" and Password as "admin123"
  And Click on Login Button
    Then I should see the dashboard page