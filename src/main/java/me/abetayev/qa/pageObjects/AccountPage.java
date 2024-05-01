package me.abetayev.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    WebDriver driver;

    @FindBy(xpath = "//a[text()='Orders']")
    private WebElement ordersLink;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean getDisplayStatusOfOrdersLink() {

        boolean displayStatus = ordersLink.isDisplayed();
        return displayStatus;
    }
}
