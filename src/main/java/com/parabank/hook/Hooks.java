package com.parabank.hook;

import com.parabank.driver.DriverManager;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks {

    private static final Logger log = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void beforeAll() {
    }

    @AfterAll
    public static void afterAll() {
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        log.info(String.format("Starting the scenario %s", scenario.getName()));
        DriverManager.setUpDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        log.info(String.format("Ending the scenario %s", scenario.getName()));
        DriverManager.tearDown();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {

    }

    @AfterStep()
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error(String.format("Scenario failed %s, taking screenshot and attaching to report as base64", scenario.getName()));
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

}
