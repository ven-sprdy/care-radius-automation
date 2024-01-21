package com.parabank.runner;

import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com/parabank/hook", "com/parabank/stepdefinitions"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        tags = "@Regression or  @Smoke"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterClass
    public void setReportInfo() {
        ExtentService.getInstance().setSystemInfo("App", "Para Bank");
        ExtentService.getInstance().setSystemInfo("Platform",  System.getProperty("platform"));
        ExtentService.getInstance().setSystemInfo("Environment", System.getProperty("env"));
        ExtentService.getInstance().setSystemInfo("Browser", System.getProperty("browser"));
        ExtentService.getInstance().setSystemInfo("Headless", System.getProperty("headless"));
        ExtentService.getInstance().setSystemInfo("OS", System.getProperty("os.name"));
        ExtentService.getInstance().setSystemInfo("OS.Version", System.getProperty("os.version"));
        ExtentService.getInstance().setSystemInfo("Grid", System.getProperty("grid"));
    }

}
