package driver.basetest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.platforms.PlatformReader;
import config.url.UrlModel;
import config.url.UrlReader;
import enums.PlatformType;
import interfaces.Platform;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;
import reports.ExtentManager;
import reports.ExtentTestManager;
import driver.browser.DriverFactory;
import utilities.screenshot.GetScreenshot;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class TestBase extends TestListenerAdapter {

    @BeforeMethod(alwaysRun = true)
    public synchronized void setupTest(Method testMethod) {
        ExtentTest testName = ExtentTestManager.createTest(testMethod.getName());
        DriverFactory.setTestLog(testName);
        Optional<Platform> platform = new PlatformReader().read().stream().findFirst();
        getJsonObject(platform.get());
        DriverFactory.getTestLog().log(Status.INFO, "<pre>Starting test method: <b>" +testMethod.getName()+"</b></pre>");
        DriverFactory.setDriver(platform);
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        JavascriptExecutor jse = ((JavascriptExecutor) DriverFactory.getDriver());
        if (platform.get().getPlatform().equals(PlatformType.WEB)) {
            try {
                DriverFactory.getDriver().manage().window().maximize();
            } catch (Exception ex) {
            }
        }
        Optional<UrlModel> baseUrl = new UrlReader().read().stream().findFirst();
        DriverFactory.getDriver().get(baseUrl.get().getEnvUrl());
        DriverFactory.getTestLog().log(Status.PASS, "<pre>Navigated to: <b>" +baseUrl.get().getEnvUrl()+"</b></pre>");
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void tearDownTest(ITestResult result, Method testMethod) throws IOException {
        if (DriverFactory.getDriver() != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = GetScreenshot.getScreenshot(DriverFactory.getDriver(), testMethod.getName());
                DriverFactory.getTestLog().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                DriverFactory.getTestLog().fail("<pre><b>" +testMethod.getName() + "</b> failed.</pre>");
            } else if (result.getStatus() == ITestResult.SKIP) {
                DriverFactory.getTestLog().skip(result.getThrowable());
            } else {
                DriverFactory.getTestLog().pass("<pre><b>" +testMethod.getName() + "</b> test method executed sucessfully.</pre>");
            }
            DriverFactory.getDriver().quit();
            DriverFactory.getTestLog().log(Status.PASS, "<pre><b>Browser closed sucessfully.</b></pre>");
            Date date = new Date();
            DriverFactory.getTestLog().getModel().setEndTime(date);
        }
        ExtentManager.getExtent().flush();
    }

    public String getJsonObject(Object obj) {
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(obj);
            DriverFactory.getTestLog().log(Status.INFO, "<pre>" +json +"</pre>");
        } catch (JsonProcessingException e) {
            DriverFactory.getTestLog().fail(e);
        }

        return json;
    }

}
