package me.abetayev.qa.testsuites;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import me.abetayev.qa.base.Base;

public class Search extends Base {

    WebDriver driver;

    public Search() {
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
    public void verifySearch() {

    }
}