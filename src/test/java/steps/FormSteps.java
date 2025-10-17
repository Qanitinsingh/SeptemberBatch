package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.methods.FormMethods;

import java.util.Map;

public class FormSteps {

    private WebDriver driver;

    public FormSteps() {
        // Default or system property / tag
        if (Hooks.browserName == null || Hooks.browserName.isEmpty()) {
            Hooks.browserName = "chrome"; // fallback
        }

        // driver will be initialized by Hooks @Before
        driver = Hooks.driver;
    }

    // Optional step to dynamically set browser
    @Given("I open the browser {string}")
    public void openBrowser(String browserNameFromFeature) {
        if (browserNameFromFeature != null && !browserNameFromFeature.isEmpty()) {
            Hooks.browserName = browserNameFromFeature;
        }
        // driver will be launched in Hooks @Before
        driver = Hooks.driver;
    }

    @Given("I navigate to the form page {string}")
    public void navigateToFormPage(String url) {
        driver = Hooks.driver;
        FormMethods.enterURL(url, driver);
    }

    @When("I fill the form with following details")
    public void fillForm(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps(String.class, String.class).get(0);
        FormMethods.fillForm(driver, data);
    }

    @And("I submit the form")
    public void submitForm() {
        FormMethods.submitForm(driver);
    }

    @Then("I should see the form submitted successfully")
    public void verifySubmission() {
        FormMethods.verifySubmission(driver);
    }
}
