package driver.browser;

import com.aventstack.extentreports.ExtentTest;
import enums.PlatformType;
import interfaces.Platform;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;
import java.util.Optional;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private static final ThreadLocal<ExtentTest> testLog = new ThreadLocal<ExtentTest>();

    public static void setDriver(Optional<Platform> platform) {
        try {
//            Optional<Platform> platform = new PlatformReader().read().stream().findFirst();
            PlatformType platformType = platform.get().getPlatform();
            DesiredCapabilities capabilities = platform.get().getDesiredCapabilities();
            URL remoteUrl = platform.get().getRemoteUrl();
            if (platformType.equals(PlatformType.MOBILE)) {
                String os = platform.get().getOsName();
                switch(os) {
                    case "android":
                        if (remoteUrl != null) {
                            driver.set(new AndroidDriver(remoteUrl, capabilities));
                        } else{
                            driver.set(new AndroidDriver(capabilities));
                        }
                        break;
                    case "ios":
                        if (remoteUrl != null) {
                            driver.set(new IOSDriver(remoteUrl, capabilities));
                        } else{
                            driver.set(new IOSDriver(capabilities));
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported mobile os: " +os +".");
                }
            } else if (platformType.equals(PlatformType.WEB)){
                String browser = platform.get().getBrowserName();
                switch(browser) {
                    case "chrome":
                        ChromeOptions chOptions = null;
                        if (platform.get().getDesiredCapabilities() != null) {
                            chOptions = DriverOptionsManager.getChromeOptions().merge(platform.get().getDesiredCapabilities());
                        }
                        if (remoteUrl != null) {
                            assert chOptions != null;
                            driver.set(new RemoteWebDriver(remoteUrl, chOptions));
                        } else {
                            WebDriverManager.chromedriver().setup();
                            driver.set(new ChromeDriver(DriverOptionsManager.getChromeOptions()));
                        }
                        break;
                    case "firefox":
                        FirefoxOptions ffOptions = null;
                        if (platform.get().getDesiredCapabilities() != null) {
                            ffOptions = DriverOptionsManager.getFirefoxOptions().merge(platform.get().getDesiredCapabilities());
                        }
                        if (remoteUrl != null) {
                            assert ffOptions != null;
                            driver.set(new RemoteWebDriver(remoteUrl, ffOptions));
                        } else {
                            WebDriverManager.firefoxdriver().setup();
                            driver.set(new FirefoxDriver(DriverOptionsManager.getFirefoxOptions()));
//                            driver = ThreadLocal.withInitial(() -> new FirefoxDriver(DriverOptionsManager.getFirefoxOptions()));
                        }
                        break;
                    case "safari":
                        SafariOptions saOptions = null;
                        if (platform.get().getDesiredCapabilities() != null) {
                            saOptions = DriverOptionsManager.getSafariOptions().merge(platform.get().getDesiredCapabilities());
                        }
                        if (remoteUrl != null) {
                            assert saOptions != null;
                            driver.set(new RemoteWebDriver(remoteUrl, saOptions));
                        } else {
                            driver.set(new SafariDriver(DriverOptionsManager.getSafariOptions()));
                        }
                        break;
                    case "edge":
                        EdgeOptions edOptions = null;
                        if (platform.get().getDesiredCapabilities() != null) {
                            edOptions = DriverOptionsManager.getEdgeOptions().merge(platform.get().getDesiredCapabilities());
                        }
                        if (remoteUrl != null) {
                            assert edOptions != null;
                            driver.set(new RemoteWebDriver(remoteUrl, edOptions));
                        } else {
                            WebDriverManager.edgedriver().setup();
                            driver.set(new EdgeDriver(DriverOptionsManager.getEdgeOptions()));
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser type: " +browser +".");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static WebDriver getDriver () {
        return driver.get();
    }

    public static void setTestLog(ExtentTest testName) {
        testLog.set(testName);
    }

    public static ExtentTest getTestLog() {
        return testLog.get();
    }

}
