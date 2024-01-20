package reports;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.platforms.PlatformReader;
import config.url.UrlModel;
import config.url.UrlReader;
import interfaces.Platform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ExtentManager {

    public static ExtentReports extent;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM_dd_yyyy_HH_mm_ss");
    private static LocalDateTime localDateTime = LocalDateTime.now();
    private static String ldtString = formatter.format(localDateTime);
    private static String filePath = System.getProperty("user.dir") + "/target/reports/ExtentReports" +ldtString +".html";

    public synchronized static ExtentReports getExtent() {
        if (extent == null) {
            extent = new ExtentReports();

            extent.attachReporter(extentSparkReporter());

            extent.setAnalysisStrategy(AnalysisStrategy.TEST);
            Optional<UrlModel> url = new UrlReader().read().stream().findFirst();
            Optional<Platform> platform = new PlatformReader().read().stream().findFirst();

            extent.setSystemInfo("Cloud", System.getProperty("cloud"));
            extent.setSystemInfo("Platform Type", platform.get().getPlatform().name());
            extent.setSystemInfo("OS Name", platform.get().getOsName());
            extent.setSystemInfo("OS Version", platform.get().getOsVersion());
            extent.setSystemInfo("Browser Name", System.getProperty("browser"));
            extent.setSystemInfo("Environment", System.getProperty("env"));
            extent.setSystemInfo("Host Name", url.get().getEnvUrl());
            extent.setSystemInfo("Selenium Java Version", "4.16.1");
            extent.setSystemInfo("AppiumVersion", "6.1.0");
            if (platform.get().getDeviceName() != null)
                extent.setSystemInfo("Device Name", platform.get().getDeviceName());
        }

        return extent;
    }

    private static ExtentSparkReporter extentSparkReporter() {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(filePath);
//        report title
        extentSparkReporter.config().setDocumentTitle("Extent Report");
//        encoding, default = UTF-8
        extentSparkReporter.config().setEncoding("utf-8");
//        protocol (http, https)
        extentSparkReporter.config().setProtocol(Protocol.HTTPS);
//        report or build name
        extentSparkReporter.config().setReportName("Test execution report");
//         theme - standard, dark
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

        return extentSparkReporter;
    }

}
