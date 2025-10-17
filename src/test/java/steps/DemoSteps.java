  package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.methods.DemoMethods;

import java.util.List;
import java.util.Map;

public class DemoSteps{

    private WebDriver driver;

    public DemoSteps() {
        driver = Hooks.driver;
    }

    @Given("I navigate to {string}")
    public void i_navigate_to(String url) {
        DemoMethods.enterUrl(url, driver);
    }

    @When("I enter the following credentials:")
    public void i_enter_the_following_credentials(io.cucumber.datatable.DataTable dataTable) {

        // Convert DataTable to List of Maps
        List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);

        // Iterate through each set of credentials and perform login
        for (Map<String, String> credentials : credentialsList) {
            System.out.println("Map keys: " + credentials.keySet());

            String username = credentials.getOrDefault("username", "").trim();
            String password = credentials.getOrDefault("password", "").trim();

            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Skipping entry with missing username or password: " + credentials);
                continue;
            }

            System.out.println("Username: " + username + ", Password: " + password);
            DemoMethods.performLogin(driver, username, password);
        }
    }

    @When("I enter the Username as {string} and Password as {string}")
    public void i_enter_the_username_as_and_password_as(String userName, String password) {
        DemoMethods.enterUserName(driver, userName);
        DemoMethods.enterPassword(driver, password);
    }
    @And("Click on Login Button")
    public void click_on_login_button() {
        DemoMethods.clickLogin(driver);
    }

    @Then("I should see the dashboard page")
    public void i_enter_the_following_credentials() {
        DemoMethods.verifyDashboardPage(driver);
    }
}
