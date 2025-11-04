package playwright.demo;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class PlaywrightLaunch {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        var browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        var page = browser.newPage();

        page.navigate("https://alphabetaops.com/");

        System.out.println("Page title: " + page.title());
        browser.close();
        playwright.close();
    }
}
