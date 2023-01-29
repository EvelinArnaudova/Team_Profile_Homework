package profile_homework;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Driver;
import java.time.Duration;
public class TestPostsVisibility {
    private WebDriver driver;

    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    protected final void tearDownTest() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @DataProvider(name = "generateUsers")
    public Object[][] generateUsers(){
        return new Object[][]{
                {"iSkilloo", "123123aA", "iskilloo"},
        };
    }

    @Test(dataProvider = "generateUsers", priority = 1)
    public void testLogin(String user, String pass, String name){

        driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signInTitle = driver.findElement(By.xpath("//p[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInTitle));

        WebElement username = driver.findElement(By.id("defaultLoginFormUsername"));
        username.sendKeys(user);

        WebElement password = driver.findElement(By.id("defaultLoginFormPassword"));
        password.sendKeys(pass);

        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();

        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));

        Boolean isTextDisplayed = wait.until(ExpectedConditions.textToBe(By.tagName("h2"), name));
        Assert.assertTrue(isTextDisplayed);

        WebElement privatePostButton = driver.findElement(By.xpath("//label[@class=\"btn-private btn btn-primary\"]"));
        privatePostButton.click();

        WebElement privatePost = driver.findElement(By.xpath("//img[@src=\"https://i.imgur.com/itXxPff.jpg\"]"));
        wait.until(ExpectedConditions.visibilityOf(privatePost));
        privatePost.click();

        Actions clicking = new Actions(driver);
        clicking.moveByOffset(10,10).click().build().perform();

        WebElement publicPostButton = driver.findElement(By.xpath("//label[@class=\"btn-public btn btn-primary\"]"));
        publicPostButton.click();

        WebElement publicPost = driver.findElement(By.xpath("//img[@src=\"https://i.imgur.com/V6bFcYU.jpg\"]"));
        wait.until(ExpectedConditions.visibilityOf(publicPost));
        publicPost.click();

        Actions clicking1 = new Actions(driver);
        clicking1.moveByOffset(10,10).click().build().perform();

    }

}
