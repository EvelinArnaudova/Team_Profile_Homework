package profile_homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProfileTestingHomeworkSimeon {
    private WebDriver driver;

    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    protected final void setupTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    protected final void testClose() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @Test(priority = 2)
    public void testNewPostCreation() {

        driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInElement = driver.
                findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.
                findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys("iSkilloo");

        WebElement passwordField = driver
                .findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys("123123aA");

        WebElement signInButton = wait.until(ExpectedConditions.
                elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        WebElement profileLink = wait.until(ExpectedConditions.
                elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:4300/users/"));

        wait.until(ExpectedConditions.textToBe(By.tagName("h2"), "iskilloo"));

        WebElement newPostButton = wait.until(ExpectedConditions.
                elementToBeClickable(By.id("nav-link-new-post")));
        newPostButton.click();

        WebElement newPostElement = driver.
                findElement(By.xpath("//*[text()='Post a picture to " +
                        "share with your awesome followers']"));
        wait.until(ExpectedConditions.visibilityOf(newPostElement));

        WebElement caption = driver.findElement(By.xpath("//*[@formcontrolname='caption']"));
        caption.sendKeys("This is a private post");

        WebElement privatePublicButton = driver.
                findElement(By.xpath("//label[@for='customSwitch2']"));
        privatePublicButton.click();

        WebElement browseForUpload = driver.findElement(By.
                xpath("//input[@class='form-control input-lg']"));
        browseForUpload.sendKeys("D:\\pictureForUpload.jpg");

        WebElement createPostButton = driver.findElement(By.
                xpath("//button[@id='create-post']"));
        createPostButton.click();
    }


    @Test(priority = 3)
    public void testFollowersWindow() {
        driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInElement = driver.
                findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.
                findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys("iSkilloo");

        WebElement passwordField = driver
                .findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys("123123aA");

        WebElement signInButton = wait.until(ExpectedConditions.
                elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        WebElement profileLink = wait.until(ExpectedConditions.
                elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg.com:" +
                "4300/users/"));

        wait.until(ExpectedConditions.textToBe(By.tagName("h2"), "iskilloo"));
        WebElement followersShown = driver.findElement(By.id("followers"));
        followersShown.click();

        WebElement followersElement = driver.
                findElement(By.xpath("//h4[text()='Followers']"));
        wait.until(ExpectedConditions.visibilityOf(followersElement));

        WebElement following = driver.findElement(By.id("following"));
        Actions builder = new Actions(driver);
        builder.moveToElement(following, 10, 25).click().build().perform();

        following.click();
        WebElement followingElement = driver.findElement(By.
                xpath("//h4[text()='Following']"));
        wait.until(ExpectedConditions.visibilityOf(followingElement));
    }

    @Test(priority = 4)
    public void modifyPublicInfoAndCheckItOnProfilePage() {
        driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInElement = driver.
                findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.
                findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys("iSkilloo");

        WebElement passwordField = driver
                .findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys("123123aA");

        WebElement signInButton = wait.until(ExpectedConditions.
                elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        WebElement profileLink = wait.until(ExpectedConditions.
                elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg." +
                "com:4300/users/"));

        WebElement modifyProfileButton = driver.findElement(By.
                xpath("//*[@class='fas fa-user-edit ng-star-inserted']"));
        modifyProfileButton.click();
        WebElement publicInfoField = driver.findElement(By.
                xpath("//*[@formcontrolname='publicInfo']"));
        publicInfoField.clear();
        String newPublicInfoText = "Public info text is now changed...";
        publicInfoField.sendKeys(newPublicInfoText);
        WebElement saveProfileButton = driver.findElement(By.
                xpath("//*[@class='btn btn-primary']"));
        saveProfileButton.click();


        Boolean isPublicInfoTextDisplayed = wait.until(ExpectedConditions.
                textToBePresentInElementLocated(By.
                        xpath("//app-profile"), newPublicInfoText));
        Assert.assertTrue(isPublicInfoTextDisplayed, "The text differs!");

    }

    @Test(priority = 5)
    public void createPostCommentAndCheckIfItIsThere() {
        driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInElement = driver.
                findElement(By.xpath("//*[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInElement));

        WebElement userNameField = driver.
                findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys("iSkilloo");

        WebElement passwordField = driver
                .findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys("123123aA");

        WebElement signInButton = wait.until(ExpectedConditions.
                elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        WebElement profileLink = wait.until(ExpectedConditions.
                elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();

        wait.until(ExpectedConditions.urlContains("http://training.skillo-bg." +
                "com:4300/users/"));

        WebElement publicPostsButton = driver.findElement(By.
                xpath("//*[@class='btn-public btn btn-primary active']"));
        publicPostsButton.click();

       WebElement thePublicPost = driver.findElement(By.
                xpath("//div/img[@src='https://i.imgur.com/NNsx1AU.png']"));
        thePublicPost.click();

        WebElement postCommentField = driver.findElement(By.
                xpath("//input[@placeholder='Comment here']"));
        String newComment = "New comment on this public post.";
        postCommentField.sendKeys(newComment);
        postCommentField.sendKeys(Keys.RETURN);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Boolean isNewCommentDisplayed = wait.until(ExpectedConditions.
                textToBePresentInElementLocated(By.
                        xpath("//div[@class='post-modal-comments']"),
                        newComment));
        Assert.assertTrue(isNewCommentDisplayed, "The text is missing!");

    }
}
