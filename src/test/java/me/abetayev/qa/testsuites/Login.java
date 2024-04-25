package me.abetayev.qa.testsuites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import me.abetayev.qa.base.Base;
import me.abetayev.qa.utils.Utilities;

//3:46:30

public class Login extends Base{

    WebDriver driver;

    public Login() {
        super();
    }

    @BeforeMethod
    public void setup() {

        driver = initializeBrowserAndOpenApplication();
        driver.findElement(By.xpath("//nav[@id='site-navigation']//a[text()='My account']")).click();

    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }

    @Test (priority=1)
    public void verifyLoginWithValidUsernameAndPassword() {

        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(prop.getProperty("validUsername"));
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.cssSelector("button[name='login']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Orders']")).isDisplayed());
    }

    @Test (priority=2)
    public void verifyLoginWithInvalidCredentialsUsingEmail() {

        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("dEmOcstmr547");
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String actualWarningMessage = driver.findElement(By.cssSelector("div.woocommerce-notices-wrapper li")).getText();
        String expectedWarningMessage = "Unknown email address. Check again or try your username.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
    }

    @Test (priority=3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {

        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String actualWarningMessage = driver.findElement(By.cssSelector("div.woocommerce-notices-wrapper li")).getText();
        String expectedWarningMessage = "Unknown email address. Check again or try your username.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
    }

    @Test (priority=4)
    public void verifyLoginWithValidUsernameAndInvalidPassword() {

        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(prop.getProperty("validUsername"));
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("dEmOcstmr547");
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String actualWarningMessage = driver.findElement(By.cssSelector("div.woocommerce-notices-wrapper li")).getText();
        String expectedWarningMessage = "Error: The password you entered for the username";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
    }

    @Test (priority=5)
    public void verifyLoginWithoutAnyCredentials() {

        driver.findElement(By.cssSelector("input[id='username']")).sendKeys("");
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("");
        driver.findElement(By.cssSelector("button[name='login']")).click();

        String actualWarningMessage = driver.findElement(By.cssSelector("div.woocommerce-notices-wrapper li")).getText();
        String expectedWarningMessage = "ABC Error: Username is required.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
                "Expected message was not provided");
    }
}
