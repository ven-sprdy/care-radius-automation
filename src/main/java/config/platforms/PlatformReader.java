package config.platforms;

import enums.PlatformType;
import interfaces.OperatingSystem;
import interfaces.Platform;
import interfaces.Reader;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PlatformReader implements Reader<Platform> {

    @Override
    public Collection<Platform> read() {
        String browser = Optional.ofNullable(System.getProperty("browser")).orElse("chrome");
        if (Optional.ofNullable(System.getProperty("cloud")).orElse("false").equals("true")) {
            return cloudBrowser(browser);
        } else {
            return browser(browser);
        }
    }

    private Collection<Platform> cloudBrowser(String browser) {
        List<Platform> platform = new ArrayList<>();

        URL cloudUrl;
        String USERNAME = "";
        String AUTOMATE_KEY = "";

        try {
            cloudUrl = new URL("https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }

        PlatformBase sp = new PlatformBase();
        sp.setBrowserName(browser);
        sp.setRemoteUrl(cloudUrl);
        sp.setDesiredCapabilities(new DesiredCapabilities());
        sp.getDesiredCapabilities().setCapability("browserstack.debug", "true");
        sp.getDesiredCapabilities().setCapability("browserstack.networklogs", "true");
        sp.getDesiredCapabilities().setCapability("browserstack.selenium_version", "3.14.0");

        Optional<OperatingSystem> operatingSystem = new OperatingSystemReader().read().stream().findFirst();

        if (operatingSystem.isPresent()) {
            sp.setOsName(operatingSystem.get().getOsName());
            sp.setOsVersion(operatingSystem.get().getOsVersion());
            sp.getDesiredCapabilities().setCapability("browser", browser);
            sp.getDesiredCapabilities().setCapability("os_version", operatingSystem.get().getOsVersion());
            sp.getDesiredCapabilities().setCapability("os", operatingSystem.get().getOsName());

            Optional<String> mobile = Optional.ofNullable(System.getProperty("mobile"));
            if (mobile.isPresent() && mobile.get().equalsIgnoreCase("true")) {
                String deviceName = Optional.ofNullable(System.getProperty("device")).orElse("");
                sp.getDesiredCapabilities().setCapability("real-mobile", true);
                sp.getDesiredCapabilities().setCapability("device", deviceName);
                sp.setPlatform(PlatformType.MOBILE);
            } else {
//                sp.getDesiredCapabilities().setCapability("resolution", "1920x1080");
                sp.setPlatform(PlatformType.WEB);
            }
        }

        platform.add(sp);

        return platform;
    }

    private Collection<Platform> browser(String browser) {
        List<Platform> platform = new ArrayList<>();

        PlatformBase sp = new PlatformBase();
        Optional<OperatingSystem> operatingSystem = new OperatingSystemReader().read().stream().findFirst();

        sp.setBrowserName(browser);
        sp.setOsName(operatingSystem.get().getOsName());
        sp.setOsVersion(operatingSystem.get().getOsVersion());
        sp.setPlatform(PlatformType.WEB);

        platform.add(sp);

        return platform;
    }

}
