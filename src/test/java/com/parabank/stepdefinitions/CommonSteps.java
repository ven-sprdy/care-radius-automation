package com.parabank.stepdefinitions;

import com.parabank.config.environment.EnvironmentUrlReader;
import com.parabank.driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.parabank.pageobjects.loginpage.LoginPage;

import java.util.Optional;

public class CommonSteps {

    private static final Logger log = LogManager.getLogger(CommonSteps.class);
    String envName = Optional.ofNullable(System.getProperty("env")).orElse("test");
    private final EnvironmentUrlReader environmentUrlReader = new EnvironmentUrlReader(envName);
    private final LoginPage loginPage = new LoginPage();

    @Given("User navigated to para bank application")
    public void userNavigateToUrl() {
        DriverManager.open(environmentUrlReader.getEnvName());
    }

    @Then("Validate login page title {string}")
    public void validateLoginPageTitle(String pageTitle) {
        loginPage.validateLoginPageTitle(pageTitle);
    }

}
