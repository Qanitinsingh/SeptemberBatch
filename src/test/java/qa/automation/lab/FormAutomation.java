package qa.automation.lab;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class FormAutomation extends BaseClass {
    private static final Logger logger = LoggerFactory.getLogger(FormAutomation.class);


    private static final String FIRST_NAME = "John";
    private static final String MIDDLE_NAME = "Middle";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "john.doe@example.com";
    private static final String PASSWORD = "Test@1234";
    private static final String ADDRESS = "123 Main St";
    private static final String CITY = "Los Angeles";
    private static final String STATE = "California";
    private static final String PIN_CODE = "90001";
    private static final int SLEEP_MILLIS = 1000;

    private static final By FIRST_NAME_LOCATOR = By.id("firstname");
    private static final By MIDDLE_NAME_LOCATOR = By.id("middlename");
    private static final By LAST_NAME_LOCATOR = By.id("lastname");
    private static final By EMAIL_LOCATOR = By.id("email");
    private static final By PASSWORD_LOCATOR = By.id("password");
    private static final By ADDRESS_LOCATOR = By.id("address");
    private static final By CITY_LOCATOR = By.id("city");
    private static final By STATE_LOCATOR = By.id("states");
    private static final By PIN_CODE_LOCATOR = By.id("pincode");
    private static final By SUBMIT_BUTTON_LOCATOR = By.cssSelector("#userForm .btn");

    @BeforeMethod
    public void loadRadioButtonPage() {
        try {
            driver.get("https://testing.qaautomationlabs.com/form.php");
            logger.info("Navigated to Form page.");
            Thread.sleep(2000);
        } catch (Exception e) {
            logger.error("Exception while loading form page: {}", e.getMessage(), e);
        }
    }

    @Test
    public void fillCompleteFormAndSubmit() {
        try {
            logger.info("Starting to fill the form...");

            driver.findElement(FIRST_NAME_LOCATOR).sendKeys(FIRST_NAME);
            logger.info("Entered First Name: {}", FIRST_NAME);
            Thread.sleep(500);

            driver.findElement(MIDDLE_NAME_LOCATOR).sendKeys(MIDDLE_NAME);
            logger.info("Entered Middle Name: {}", MIDDLE_NAME);
            Thread.sleep(500);

            driver.findElement(LAST_NAME_LOCATOR).sendKeys(LAST_NAME);
            logger.info("Entered Last Name: {}", LAST_NAME);
            Thread.sleep(500);

            driver.findElement(EMAIL_LOCATOR).sendKeys(EMAIL);
            logger.info("Entered Email: {}", EMAIL);
            Thread.sleep(500);

            driver.findElement(PASSWORD_LOCATOR).sendKeys(PASSWORD);
            logger.info("Entered Password: {}", PASSWORD);
            Thread.sleep(500);

            driver.findElement(ADDRESS_LOCATOR).sendKeys(ADDRESS);
            logger.info("Entered Address: {}", ADDRESS);
            Thread.sleep(500);

            driver.findElement(CITY_LOCATOR).sendKeys(CITY);
            logger.info("Entered City: {}", CITY);
            Thread.sleep(500);

            driver.findElement(STATE_LOCATOR).sendKeys(STATE);
            logger.info("Entered State: {}", STATE);
            Thread.sleep(500);

            driver.findElement(PIN_CODE_LOCATOR).sendKeys(PIN_CODE);
            logger.info("Entered Pin Code: {}", PIN_CODE);
            Thread.sleep(500);

            driver.findElement(SUBMIT_BUTTON_LOCATOR).click();
            logger.info("Form submitted successfully!");
        } catch (Exception e) {
            logger.error("Exception while filling form: {}", e.getMessage(), e);
        }
    }

}