package steps;

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
        driver = Hooks.driver; // Initialize driver from Hooks class
    }

    @Given("I navigate to the form page {string}")
    public void navigateToFormPage(String url) {
        FormMethods.enterURL(url, driver);

    }

    @When("I fill the form with following details")
    public void fillForm(Map<String, String> data) {
        // Call method from FormMethods class and pass data map
        // formMethods.fillForm(data);
    }

    @And("I submit the form")
    public void submitForm() {
        // Call method from FormMethods class
        // formMethods.submitForm();
    }

    @Then("I should see the form submitted successfully")
    public void verifySubmission() {
        // Call method from FormMethods class
        // formMethods.verifySubmission();
    }
}
