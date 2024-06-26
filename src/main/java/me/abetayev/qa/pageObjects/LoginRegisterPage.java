package me.abetayev.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginRegisterPage {

    WebDriver driver;

    @FindBy(css = "#username")
    private WebElement usernameField;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @FindBy(css = "input#reg_email")
    private WebElement registerEmail;

    @FindBy(css = "button[name='login']")
    private WebElement loginButton;

    @FindBy(xpath = "//button[text()='Register']")
    private WebElement registerButton;

    @FindBy(css = "div.woocommerce-notices-wrapper li")
    private WebElement invalidLoginErrorMessage;

    public LoginRegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AccountPage clickOnLoginButton() {
        loginButton.click();
        return new AccountPage(driver);
    }

    public AccountPage login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return new AccountPage(driver);
    }

    public AccountPage register(String email) {
        registerEmail.sendKeys(email);
        registerButton.click();
        return new AccountPage(driver);
    }

    public String retrieveInvalidLoginErrorMessageText() {
        String errorText = invalidLoginErrorMessage.getText();
        return errorText;
    }
}
