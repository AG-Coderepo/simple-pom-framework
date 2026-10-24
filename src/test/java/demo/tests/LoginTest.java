package demo.tests;

import demo.pages.LoginPage;
import java.net.URI;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;

    /**
     * Runs before each test method and creates a fresh Chrome session.
     * Uses local Chrome unless SELENIUM_REMOTE_URL points to a Selenium server.
     * HEADLESS=false makes local Chrome visible. Limits page loading to 30 seconds.
     *
     * @throws Exception if the remote URL is invalid or browser setup fails
     */
    @BeforeMethod
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1280,800");
        if (!"false".equalsIgnoreCase(System.getenv("HEADLESS"))) {
            options.addArguments("--headless=new");
        }
        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
        driver = remoteUrl == null || remoteUrl.isBlank()
                ? new ChromeDriver(options)
                : new RemoteWebDriver(URI.create(remoteUrl).toURL(), options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    /**
     * Opens the demo login page and submits its public sample credentials.
     * Verifies both the successful-login message and the secure page URL.
     * Credentials remain hardcoded for Stage 1; properties are a later stage.
     */
    @Test
    public void validUserCanLogin() {
        LoginPage page = new LoginPage(driver);
        page.open();
        // Public credentials published by this demo website.
        page.login("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(page.getSuccessMessage().contains("You logged into a secure area!"));
        Assert.assertEquals(URI.create(driver.getCurrentUrl()).getPath(), "/secure");
    }

    /**
     * Runs after each test, including failed tests, and closes the browser session.
     * Checks for null so cleanup also works when setup could not create a driver.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
