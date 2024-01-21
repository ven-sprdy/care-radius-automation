package com.parabank.pageobjects.loginpage;

import com.parabank.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.parabank.util.WebActions;

public class LoginPage extends WebActions {

    private static final Logger log = LogManager.getLogger(LoginPage.class);

    public LoginPage() {
        log.info("In Login page");
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(css = "input[name='username']")
    private WebElement usernameInput;

    @FindBy(css = "input[name='password']")
    private WebElement passwordInput;

    @FindBy(css = "input.button")
    private WebElement loginButton;

    @FindBy(css = "p.error")
    private  WebElement inValidLoginError;

    public void validateLoginPageTitle(String pageTitle) {
        assertPageTitle(pageTitle);
    }

    public void enterUsername(String username) {
        sendKeys(usernameInput, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public void loginWithUsernameAndPassword(String username, String password) {
        sendKeys(usernameInput, username);
        sendKeys(passwordInput, password);
        clickElement(loginButton);
    }

    public void validateInValidLoginError(String errorMessage) {
        assertEquals(inValidLoginError.getText().trim(), errorMessage);
    }

}
