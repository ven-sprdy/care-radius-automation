package driver.browser;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverOptionsManager {

    //Get Chrome Options
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        if (Boolean.parseBoolean(System.getProperty("headless")))
            options.addArguments("--headless");
//        options.addArguments("--start-maximized");
//        options.addArguments("--ignore-certificate-errors");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--incognito");
        return options;
    }

    //Get Firefox Options
    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions ffOptions = new FirefoxOptions();
        if (Boolean.parseBoolean(System.getProperty("headless")))
            ffOptions.addArguments("--headless");
//        Accept Untrusted Certificates
//        profile.setAcceptUntrustedCertificates(true);
//        profile.setAssumeUntrustedCertificateIssuer(false);
//        Use No Proxy Settings
//        profile.setPreference("network.proxy.type", 0);
//        Set Firefox profile to capabilities

        return ffOptions;
    }

    //Get Safari Options
    public static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(false);

        if (Boolean.parseBoolean(System.getProperty("headless")))
            throw new IllegalStateException(String.format("Headless not supported for Safari browser"));
        return options;
    }

    //Get Edge Options
    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        if (Boolean.parseBoolean(System.getProperty("headless")))
            options.addArguments("--headless");
        return options;
    }

}
