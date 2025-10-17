package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    public static WebDriver driver;
    public static String browserName; // can be set from system property or tag
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup(Scenario scenario) {
        try {
            // Step 1: Check system property
            String browserFromProperty = System.getProperty("browser");

            // Step 2: Check scenario tags
            String browserFromTag = scenario.getSourceTagNames().stream()
                    .filter(tag -> tag.matches("@(chrome|firefox|edge)"))
                    .map(tag -> tag.replace("@", ""))
                    .findFirst()
                    .orElse(null);

            // Step 3: Decide browser to launch
            if (browserFromProperty != null && !browserFromProperty.isEmpty()) {
                browserName = browserFromProperty;
            } else if (browserFromTag != null) {
                browserName = browserFromTag;
            } else {
                browserName = "chrome";
            }

            logger.info("Launching browser: {}", browserName);

            // Step 4: Launch driver dynamically using if-else
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else {
                logger.warn("Browser '{}' not recognized. Launching Chrome as default.", browserName);
                driver = new ChromeDriver();
                browserName = "chrome";
            }

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            logger.info("{} browser launched, window maximized, and cookies cleared.", browserName);

        } catch (Exception e) {
            logger.error("Exception during WebDriver setup for {}: {}", browserName, e.getMessage(), e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed() && driver != null) {
                captureScreenshot(scenario);
            } else if (driver != null) {
                logger.info("Scenario '{}' passed.", scenario.getName());
            }
        } catch (Exception e) {
            logger.error("Exception during screenshot capture or teardown: {}", e.getMessage(), e);
        } finally {
            cleanUp();
        }
    }

    private void captureScreenshot(Scenario scenario) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";

        File screenshotDir = new File(System.getProperty("user.dir") + File.separator + "ScreenShots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotDir, screenshotName);
        Files.copy(srcFile.toPath(), destFile.toPath());

        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshotBytes, "image/png", screenshotName);

        logger.error("Scenario '{}' failed. Screenshot saved at: {}", scenario.getName(), destFile.getAbsolutePath());
    }

    private void cleanUp() {
        try {
            if (driver != null) {
                driver.quit();
                logger.info("{} browser quit successfully.", browserName);
            }

            // Kill leftover driver processes
            killDriverProcesses(browserName);

            driver = null;
        } catch (Exception e) {
            logger.error("Error during browser cleanup: {}", e.getMessage(), e);
        }
    }

    private void killDriverProcesses(String browser) {
        String os = System.getProperty("os.name").toLowerCase();
        String processName = "";

        if (browser.equalsIgnoreCase("chrome")) {
            processName = "chromedriver";
        } else if (browser.equalsIgnoreCase("firefox")) {
            processName = "geckodriver";
        } else if (browser.equalsIgnoreCase("edge")) {
            processName = "msedgedriver";
        } else {
            processName = "chromedriver";
        }

        try {
            Process process;
            if (os.contains("win")) {
                process = Runtime.getRuntime().exec("taskkill /F /IM " + processName + ".exe /T");
            } else {
                process = Runtime.getRuntime().exec("pkill -f " + processName);
            }
            process.waitFor();

            logger.info("Cleaned up any existing '{}' driver processes.", processName);
        } catch (Exception e) {
            logger.warn("Unable to kill {} process automatically: {}", processName, e.getMessage());
        }
    }
}
