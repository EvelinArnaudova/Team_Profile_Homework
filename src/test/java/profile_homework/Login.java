package profile_homework;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Login {
    public WebDriver driver;

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
                {"testskillo@gmail.com", "Test1234", "TestSkillo123456"},
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

        /*WebElement modifyProfile = driver.findElement(By.xpath("//h2[text()='TestSkillo123456']/following-sibling::i"));
        wait.until(ExpectedConditions.elementToBeClickable(modifyProfile));
        modifyProfile.click();

        WebElement modifyTitleText = driver.findElement(By.tagName("h4"));
        wait.until(ExpectedConditions.visibilityOf(modifyTitleText));

        WebElement publicInfoClear = driver.findElement(By.xpath("//textarea[@formcontrolname=\"publicInfo\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(publicInfoClear));
        publicInfoClear.clear();

        WebElement clearSave = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(clearSave));
        clearSave.click();

        WebElement publicInfoField = driver.findElement(By.xpath("//textarea[@formcontrolname=\"publicInfo\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(publicInfoField));
        publicInfoField.sendKeys("My info");

        WebElement saveButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();*/

    }

}
