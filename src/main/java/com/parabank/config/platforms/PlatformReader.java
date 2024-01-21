package com.parabank.config.platforms;

import com.parabank.interfaces.OperatingSystem;
import com.parabank.config.properties.PropertiesConfigReader;
import com.parabank.enums.PlatformType;
import com.parabank.interfaces.Platform;
import com.parabank.interfaces.Reader;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PlatformReader implements Reader<Platform> {

    private final PropertiesConfigReader propertiesConfigReader = new PropertiesConfigReader();

    @Override
    public Collection<Platform> read() {
        String browser = Optional.ofNullable(System.getProperty("browser")).orElse("chrome");
        boolean isGrid = Boolean.parseBoolean(Optional.ofNullable(System.getProperty("grid")).orElse("false"));
        System.setProperty("grid", String.valueOf(isGrid));
        if (isGrid) {
            return cloudBrowser(browser);
        } else {
            return browser(browser);
        }
    }

    private Collection<Platform> cloudBrowser(String browser) {
        List<Platform> platform = new ArrayList<>();

        URL cloudUrl;
        try {
            cloudUrl = new URL(propertiesConfigReader.getProperty("remote.url"));
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }

        PlatformBase sp = new PlatformBase();
        sp.setBrowserName(browser);
        sp.setRemoteUrl(cloudUrl);
        sp.setDesiredCapabilities(new DesiredCapabilities());

        Optional<OperatingSystem> operatingSystem = new OperatingSystemReader().read().stream().findFirst();

        if (operatingSystem.isPresent()) {
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
