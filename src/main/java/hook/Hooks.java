package hook;

import io.cucumber.java.*;

public class Hooks {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("================ BEFORE ALL ================");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("================ AFTER ALL ================");

    }

    @Before
    public void beforeScenario(Scenario scenario)
    {
//        launchDriver();
//        log.info("Scenario Executing Start :-"+scenario.getName());

    }

    //just like @afterMethod
    @After
    public void afterScenario(Scenario scenario)
    {
        //validate if scenario has failed
//        if(scenario.isFailed()) {
//            scenario.attach(Util.takeScreenShot(), "image/png", scenario.getName());
//        }
//        log.info("Scenario Executing Finish :-"+scenario.getName());
//        tearDown();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        //validate if scenario has failed then Screenshot
//        if (scenario.isFailed()) {
//            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", "Screenshot Failed");

            //AllureManager.takeScreenshotStep();
//        }
    }

}
