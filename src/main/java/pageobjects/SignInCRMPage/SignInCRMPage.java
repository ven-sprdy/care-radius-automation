package pageobjects.SignInCRMPage;

import data.CommonConstants;
import driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignInCRMPage {

    public SignInCRMPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(css = "input[name='email']")
    private WebElement inputEmail;

    @FindBy(css = "input[name='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[normalize-space()='Sign in']")
    private WebElement buttonSignIn;

    @FindBy(css = ".alert-danger")
    private WebElement invalidAuthErrorMsg;

    public void signIn(String email, String password) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        buttonSignIn.click();
    }

    public void validateInvalidAuthMsg() {
        Assert.assertEquals(invalidAuthErrorMsg.getText().trim(), CommonConstants.INVALID_AUTHENTICATION_ERROR_MSG);
    }

}
