package config.platforms;

import enums.PlatformType;
import interfaces.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class PlatformBase implements Platform {

    protected String deviceName;
    protected String osName;
    protected String osVersion;
    protected PlatformType platform;
    protected String browserName;
    protected String browserVersion;
    protected URL remoteUrl;
    protected DesiredCapabilities desiredCapabilities;

    public PlatformBase() {
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) { this.osName = osName; }

    public String getOsVersion() { return osVersion; }

    public void setOsVersion(String osVersion) { this.osVersion = osVersion; }

    public PlatformType getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformType platform) {
        this.platform = platform;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public URL getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(URL remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public DesiredCapabilities getDesiredCapabilities() {
        return desiredCapabilities;
    }

    public void setDesiredCapabilities(DesiredCapabilities desiredCapabilities) {
        this.desiredCapabilities = desiredCapabilities;
    }

}
