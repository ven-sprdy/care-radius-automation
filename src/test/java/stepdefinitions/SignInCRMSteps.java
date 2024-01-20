package stepdefinitions;

import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage.HomePage;
import pageobjects.SignInCRMPage.SignInCRMPage;
import util.WebActions;

public class SignInCRMSteps {

    private final SignInCRMPage signInCRMPage = new SignInCRMPage();
    private final HomePage homePage = new HomePage();

    @Given("User navigate to url {string}")
    public void userNavigateToUrl(String url) {
        DriverManager.open(url);
    }

    @When("User login with valid username {string} and password {string}")
    public void userLoginWithValidUsernameAndPassword(String email, String password) {
        signInCRMPage.signIn(email, password);
    }

    @When("User login with invalid username {string} and password {string}")
    public void userLoginWithInvalidUsernameAndPassword(String email, String password) {
        signInCRMPage.signIn(email, password);
    }

    @Then("The user redirect to Dashboard page")
    public void theUserRedirectToDashboardPage() {
        WebActions.waitForPageLoaded();
        homePage.validatePageTitleAndUrl();
    }

    @Then("The error message is displayed")
    public void theErrorMessageIsDisplays() {
        signInCRMPage.validateInvalidAuthMsg();
    }

}
