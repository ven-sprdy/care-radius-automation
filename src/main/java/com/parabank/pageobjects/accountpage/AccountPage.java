package com.parabank.pageobjects.accountpage;

import com.parabank.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.parabank.util.WebActions;

public class AccountPage extends WebActions {

    private static Logger log = LogManager.getLogger(AccountPage.class);

    public AccountPage() {
        log.info("In Account Page");
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(xpath = ".//*[text()='Log Out']")
    private WebElement logoutLink;

    public void validateAccountPageTitle(String pageTitle) {
        assertPageTitle(pageTitle);
    }

    public void clickLogoutLink() {
        clickElement(logoutLink);
    }

}