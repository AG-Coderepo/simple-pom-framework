package demo.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By submit = By.cssSelector("button[type='submit']");

    /**
     * Shares the browser created by the test and prepares a 15-second explicit wait.
     * This constructor does not create another browser.
     *
     * @param driver browser session that this page object will control
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /** Navigates the shared browser to the public practice login page. */
    public void open() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    /**
     * Waits for the username field, enters credentials, and clicks Login.
     * Assertions belong in the test rather than in this reusable page action.
     *
     * @param user username to enter
     * @param pass password to enter
     */
    public void login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(submit).click();
    }

    /**
     * Waits for the logout link to show that navigation completed, then reads the message.
     *
     * @return displayed notification text for the test to assert against
     */
    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/logout']")));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash"))).getText();
    }
}
