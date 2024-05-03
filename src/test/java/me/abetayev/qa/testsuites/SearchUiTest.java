package me.abetayev.qa.testsuites;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import me.abetayev.qa.base.Base;

public class SearchUiTest extends Base {

    public WebDriver driver;

    public SearchUiTest() {
        super();
    }

    @BeforeMethod
    public void setup() {

        driver = initializeBrowserAndOpenApplication();
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }

    @Test(priority = 1)
    public void verifyExistingProductSearch() {
        boolean testFail = false;
        Assert.assertTrue(testFail);
    }

    @Test(priority = 2, dependsOnMethods = {"verifyExistingProductSearch"})
    public void verifyNonExistentProductSearch() {

    }
}