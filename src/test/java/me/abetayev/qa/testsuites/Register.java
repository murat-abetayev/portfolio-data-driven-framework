package me.abetayev.qa.testsuites;

import me.abetayev.qa.pageObjects.AccountPage;
import me.abetayev.qa.pageObjects.HomePage;
import me.abetayev.qa.pageObjects.LoginRegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import me.abetayev.qa.base.Base;
import me.abetayev.qa.utils.Utilities;

public class Register extends Base {

    WebDriver driver;
    LoginRegisterPage loginRegisterPage;
    AccountPage accountPage;

    public Register() {
        super();
    }

    @BeforeMethod
    public void setup() {

        driver = initializeBrowserAndOpenApplication();
        HomePage homePage = new HomePage(driver);
        loginRegisterPage = homePage.clickOnMyAccount();
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }

    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithEmail() {

        accountPage = loginRegisterPage.register(Utilities.generateEmailWithTimeStamp());

        Assert.assertTrue(accountPage.getDisplayStatusOfOrdersLink());
    }

    @Test(priority = 2)
    public void verifyRegisteringAccountWithExistingEmail() {

        loginRegisterPage.register(dataProp.getProperty("existingCustomerEmail"));

        Assert.assertTrue(loginRegisterPage.retrieveInvalidLoginErrorMessageText()
                .contains(dataProp.getProperty("accountExistsErrorMessage")));
    }
}