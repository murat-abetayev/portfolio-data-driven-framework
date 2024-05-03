package me.abetayev.qa.testsuites;

import me.abetayev.qa.base.Base;
import me.abetayev.qa.pageObjects.AccountPage;
import me.abetayev.qa.pageObjects.HomePage;
import me.abetayev.qa.pageObjects.LoginRegisterPage;
import me.abetayev.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginUiTest extends Base {

    public WebDriver driver;
    LoginRegisterPage loginRegisterPage;
    AccountPage accountPage;

    public LoginUiTest() {
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

    @Test(priority = 1, dataProvider = "validCredentialsSupplier")
    public void verifyLoginWithValidUsernameAndPassword(String username, String password) {

        accountPage = loginRegisterPage.login(username, password);
        Assert.assertTrue(accountPage.getDisplayStatusOfOrdersLink());
    }

    @DataProvider(name = "validCredentialsSupplier")
    public Object[][] supplyTestData() {

        Object[][] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidEmailAndInvalidPassword() {

        loginRegisterPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));

        Assert.assertTrue(loginRegisterPage.retrieveInvalidLoginErrorMessageText()
                .contains(dataProp.getProperty("invalidEmailErrorMessage")));
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {

        loginRegisterPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("validPassword"));

        Assert.assertTrue(loginRegisterPage.retrieveInvalidLoginErrorMessageText()
                .contains(dataProp.getProperty("invalidEmailErrorMessage")));
    }

    @Test(priority = 4)
    public void verifyLoginWithValidUsernameAndInvalidPassword() {

        loginRegisterPage.login(dataProp.getProperty("validUsername"), dataProp.getProperty("invalidPassword"));

        Assert.assertTrue(loginRegisterPage.retrieveInvalidLoginErrorMessageText()
                .contains(dataProp.getProperty("invalidPasswordErrorMessage")));
    }

    @Test(priority = 5)
    public void verifyLoginWithoutAnyCredentials() {

        loginRegisterPage.clickOnLoginButton();

        Assert.assertTrue(loginRegisterPage.retrieveInvalidLoginErrorMessageText()
                .contains(dataProp.getProperty("usernameRequiredErrorMessage")));
    }
}
