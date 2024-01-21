package com.parabank.stepdefinitions.loginsteps;

import com.parabank.pageobjects.loginpage.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginSteps {

    private static final Logger log = LogManager.getLogger(LoginSteps.class);
    private final LoginPage loginPage = new LoginPage();

    @When("User login with valid username {string} and password {string}")
    public void userFilledUsernameAndPassword(String username, String password) {
        loginPage.loginWithUsernameAndPassword(username, password);
    }

    @Then("Validate authentication error {string}")
    public void validateInvalidLoginError(String errorMessage) {
        loginPage.validateInValidLoginError(errorMessage);
    }

    @Then("Validate authentication error page title {string}")
    public void validateAuthenticationErrorPageTitle(String errorPageTitle) {
        loginPage.validateLoginPageTitle(errorPageTitle);
    }

}
