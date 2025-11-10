package database.testing;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpFormAutomation extends SqlConnector {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    public static String otpFromDB;   // Shared between tests

    @BeforeClass
    public void setup() throws Exception {

        // ✅ Initialize DB connection (from SqlConnector)
        createConnection();

        // ✅ Initialize Playwright
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
    }

    @Test(priority = 1)
    public void uiFormFillAndGenerateOtp() {

        page.navigate("https://alphabetaops.com/");
        page.setViewportSize(1920, 1080);

        // Click Database Testing Menu
        page.locator(".navbar-nav li:nth-of-type(7) a").click();

        // Click the novice section
        page.locator(".mb-2:first-child").click();
        page.locator(".mb-2:first-child ul.list-group li.list-group-item:first-child").click();

        // Click first list item (textbox)
        page.locator(".mb-2:first-child ul.list-group li.list-group-item:first-child li a").click();

        // Switch to iframe
        FrameLocator iframe = page.frameLocator("iframe.content-iframe");

        iframe.locator("#fullName").fill("Nitin");
        iframe.locator("#email").fill("Nitin@yahoo.com");
        iframe.locator("#password").fill("Nitin@123");
        iframe.locator("#generateOtpBtn").click();
    }

    @Test(priority = 2, dependsOnMethods = "uiFormFillAndGenerateOtp")
    public void fetchOtpFromDatabaseAndSubmit() throws Exception {

        otpFromDB = getOTPFromDB();
        Assert.assertNotNull(otpFromDB, " OTP not found in DB!");

        System.out.println("✅ OTP Fetched from DB: " + otpFromDB);

        FrameLocator iframe = page.frameLocator("iframe.content-iframe");

        iframe.locator("#otpField").fill(otpFromDB);
        iframe.locator("#submitFormBtn").click();
    }

    /** ✅ DB method to fetch OTP */
    public static String getOTPFromDB() throws Exception {
        Connection conn = createConnection();
        String query = "SELECT otp FROM authentication ORDER BY id DESC LIMIT 1";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("otp");
        }
        return null;
    }

    @AfterClass
    public void tearDown() throws Exception {

        if (browser != null)
            browser.close();
        if (playwright != null)
            playwright.close();

        closeConnection();  // from SqlConnector
    }
}
