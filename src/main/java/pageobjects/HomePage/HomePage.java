package pageobjects.HomePage;

import driver.DriverManager;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {

    public String pageTitle = "Dashboard | RISE - Ultimate Project Manager and CRM";
    public String pageUrl = "/dashboard";

    public HomePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void validatePageTitleAndUrl() {
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains(pageUrl));
        Assert.assertEquals(DriverManager.getDriver().getTitle().trim(), pageTitle);
    }

}
