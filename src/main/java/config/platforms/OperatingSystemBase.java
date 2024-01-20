package config.platforms;

import interfaces.OperatingSystem;

public class OperatingSystemBase implements OperatingSystem {

    protected String osName;
    protected String osVersion;

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

}
