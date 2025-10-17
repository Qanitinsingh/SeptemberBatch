package page.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Map;

public class FormMethods {


    private static final Logger logger = LoggerFactory.getLogger(FormMethods.class);


// locators
    private static final By FIRST_NAME_LOCATOR = By.id("firstname");
    private static final By MIDDLE_NAME_LOCATOR = By.id("middlename");
    private static final By LAST_NAME_LOCATOR = By.id("lastname");
    private static final By EMAIL_LOCATOR = By.id("email");
    private static final By PASSWORD_LOCATOR = By.id("password");
    private static final By ADDRESS_LOCATOR = By.id("address");
    private static final By CITY_LOCATOR = By.id("city");
    private static final By STATE_LOCATOR = By.id("states");
    private static final By PIN_CODE_LOCATOR = By.id("pincode");
    private static final By SUBMIT_BUTTON_LOCATOR = By.cssSelector("#userForm .bfsdfdstn");
    // Navigate to URL


    public static void enterURL(String url , WebDriver driver) {
        try {
            driver.get(url);

            logger.info("Navigated to URL: {}", url);
        } catch (Exception e) {
            logger.error("Exception while navigating to URL {}: {}", url, e.getMessage(), e);
        }
    }

    // Fill the form with Map of data
    public static void fillForm(WebDriver driver, Map<String, String> data) {
        try {

            driver.findElement(FIRST_NAME_LOCATOR).sendKeys(data.get("firstName"));
            logger.info("Entered First Name: {}", data.get("firstName"));

            driver.findElement(MIDDLE_NAME_LOCATOR).sendKeys(data.get("middleName"));
            logger.info("Entered Middle Name: {}", data.get("middleName"));

            driver.findElement(LAST_NAME_LOCATOR).sendKeys(data.get("lastName"));
            logger.info("Entered Last Name: {}", data.get("lastName"));

            driver.findElement(EMAIL_LOCATOR).sendKeys(data.get("email"));
            logger.info("Entered Email: {}", data.get("email"));

            driver.findElement(PASSWORD_LOCATOR).sendKeys(data.get("password"));
            logger.info("Entered Password: {}", data.get("password"));

            driver.findElement(ADDRESS_LOCATOR).sendKeys(data.get("address"));
            logger.info("Entered Address: {}", data.get("address"));

            driver.findElement(CITY_LOCATOR).sendKeys(data.get("city"));
            logger.info("Entered City: {}", data.get("city"));

            driver.findElement(STATE_LOCATOR).sendKeys(data.get("state"));
            logger.info("Entered State: {}", data.get("state"));

            driver.findElement(PIN_CODE_LOCATOR).sendKeys(data.get("pinCode"));
            logger.info("Entered Pin Code: {}", data.get("pinCode"));

        } catch (Exception e) {
            logger.error("Exception while filling form: {}", e.getMessage(), e);
        }
    }

    // Submit the form
    public static void submitForm(WebDriver driver) {

            driver.findElement(SUBMIT_BUTTON_LOCATOR).click();
            logger.info("Form submitted successfully!");

    }

    // Verify submission
    public static void verifySubmission(WebDriver driver) {
        // You can add verification logic here, e.g., check confirmation message
        logger.info("Form submission verification executed.");
    }
}
