package me.abetayev.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    @FindBy(xpath = "//nav[@id='site-navigation']//a[text()='My account']")
    private WebElement myAccountMenuElement;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginRegisterPage clickOnMyAccount() {
        myAccountMenuElement.click();
        return new LoginRegisterPage(driver);
    }
}
