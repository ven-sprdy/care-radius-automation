package utilities.screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class GetScreenshot {

    public static String getScreenshot(WebDriver driver, String testName) {
        TakesScreenshot screenshot =  ((TakesScreenshot) driver);

        File src = screenshot.getScreenshotAs(OutputType.FILE);
        String path =System.getProperty("user.dir")+"/target/reports/screenshots/" +testName +System.currentTimeMillis() +".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return path;
    }

}
