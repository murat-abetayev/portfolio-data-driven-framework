package me.abetayev.qa.testsuites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import me.abetayev.qa.base.Base;
import me.abetayev.qa.utils.Utilities;

public class Register extends Base{

    WebDriver driver;

    public Register() {
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
    public void verifyRegisteringAnAccountWithEmail() {

        driver.findElement(By.cssSelector("input#reg_email")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.xpath("//button[text()='Register']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Orders']")).isDisplayed());
    }

    @Test (priority=2)
    public void verifyRegisteringAccountWithInvalidEmail() {
        driver.findElement(By.cssSelector("input#reg_email")).sendKeys("demouser");
        driver.findElement(By.xpath("//button[text()='Register']")).click();

    }
}