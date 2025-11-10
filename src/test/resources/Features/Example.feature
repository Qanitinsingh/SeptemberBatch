Feature: Verify the logo click functionality

  @SmokeTest
  Scenario: User click on logo
    Given User is on the "https://testing.qaautomationlabs.com/index.php" in chrome
    When User clicks on the logo
    Then User should be redirected to the homepage