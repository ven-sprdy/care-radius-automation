package interfaces;

import enums.PlatformType;

public interface Platform extends BrowserCapabilities, Browser, OperatingSystem {

    String getDeviceName();

    void setDeviceName(String deviceName);

    PlatformType getPlatform();

    void setPlatform(PlatformType platform);

}
