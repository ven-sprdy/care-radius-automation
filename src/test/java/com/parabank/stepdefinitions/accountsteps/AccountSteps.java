package com.parabank.stepdefinitions.accountsteps;

import com.parabank.pageobjects.accountpage.AccountPage;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountSteps {

    private static final Logger log = LogManager.getLogger(AccountSteps.class);
    private final AccountPage accountPage = new AccountPage();

    @Then("Validate account page title {string}")
    public void validateAccountPageTitle(String accountPageTitle) {
        accountPage.validateAccountPageTitle(accountPageTitle);
    }

    @Then("User logs out of application")
    public void userLogsOut() {
        accountPage.clickLogoutLink();
    }

}
