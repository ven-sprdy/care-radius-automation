package com.parabank.util;

import com.parabank.data.CommonConstants;
import com.parabank.driver.DriverManager;
import com.parabank.enums.SelectType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebActions {

    private static final Logger log = LogManager.getLogger(WebActions.class);
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    protected Actions actions;
    
    public  void waitForPageLoaded() {
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(CommonConstants.WAIT_PAGE_LOADED), Duration.ofMillis(500));
        js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            //Wait for Javascript to load
            wait.until(jsLoad);
        }
    }

    public  void waitForPageLoaded(int timeOut) {
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
        js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            //Wait for Javascript to load
            wait.until(jsLoad);
        }
    }

    public void assertPageTitle(String pageTitle) {
        Assert.assertEquals(DriverManager.getDriver().getTitle(), pageTitle, String.format("Page title :%s", DriverManager.getDriver().getTitle()));
        log.info(String.format("Page title :%s", DriverManager.getDriver().getTitle()));
    }

    public  void clickElement(WebElement element) {
        waitForVisibilityOfElement(element);
        moveToElementAndClick(element);
    }

    public  void clickElement(WebElement element, int timeInSeconds) {
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofMillis(500));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        moveToElementAndClick(element);
    }

    public  void sendKeys(WebElement element, String searchString) {
        waitForVisibilityOfElement(element);
        moveToElementAndClick(element);
        element.clear();
        element.sendKeys(searchString);
        log.info(String.format("Entered input: %s", searchString));
    }

    public  void sendKeys(WebElement element, String searchString, int timeInSeconds) {
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofMillis(500));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        moveToElementAndClick(element);
        element.clear();
        element.sendKeys(searchString);
    }

    public  void select(WebElement element, SelectType selectType, String selectString) {
        Select select = new Select(element);
        switch (selectType) {
            case SELECT_BY_INDEX:
                select.selectByIndex(Integer.parseInt(selectString));
                break;
            case SELECT_BY_TEXT:
                select.selectByVisibleText(selectString);
                break;
            case SELECT_BY_VALUE:
                select.selectByValue(selectString);
                break;
            default:
                break;
        }
    }

    public  void switchToIframe(WebElement element) {
        DriverManager.getDriver().switchTo().frame(element);
    }

    public  void switchToDefaultContent() {
        DriverManager.getDriver().switchTo().defaultContent();
    }

    public  List<String> getListElementsText(List<WebElement> listElements) {
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(CommonConstants.WEBDRIVER_WAIT_MINIMUM), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfAllElements(listElements));
        List<String> listText = new ArrayList<>();
        for (WebElement element : listElements) {
            listText.add(element.getText());
        }
        return listText;
    }

    public  void waitForVisibilityOfElement(WebElement element) {
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(CommonConstants.WEBDRIVER_WAIT_MINIMUM), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public  boolean isDisplayed(WebElement element) {
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(CommonConstants.WEBDRIVER_WAIT_MINIMUM), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public void moveToElementAndClick(WebElement element) {
        actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(element).click().build().perform();
    }

    public void waitUntilSpinnerNotDisplayed(WebElement element) {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(element.getAttribute("style").equalsIgnoreCase(CommonConstants.SPINNER_STYLE))
                break;
        }
    }

    public void assertEquals(String actualResult, String expectedResults) {
        Assert.assertEquals(actualResult, expectedResults);
        log.info(String.format("Assertion completed %s", actualResult));
    }

}
