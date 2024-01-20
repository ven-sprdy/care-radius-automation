package config.platforms;

import interfaces.OperatingSystem;
import interfaces.Reader;

import java.util.ArrayList;
import java.util.Collection;

public class OperatingSystemReader implements Reader<OperatingSystem> {

    @Override
    public Collection<OperatingSystem> read() {
        Collection<OperatingSystem> operatingSystems = new ArrayList<>();
        String os = System.getProperty("os-name");
        String osVersion = System.getProperty("os-version");
        String localOs = System.getProperty("os.name");
        String localOsVersion = System.getProperty("os.version");

        OperatingSystem operatingSystem = new OperatingSystemBase();

        if (os != null && !os.equals("")) {
            operatingSystem.setOsName(os);
        } else {
            operatingSystem.setOsName(localOs);
        }

        if (osVersion != null && !osVersion.equals("")) {
                operatingSystem.setOsVersion(osVersion);
        } else {
            operatingSystem.setOsVersion(localOsVersion);
        }
        operatingSystems.add(operatingSystem);
//        if (os != null && !os.equals("")) {
//            OperatingSystem operatingSystem = new OperatingSystemBase();
//            operatingSystem.setOsName(os);
//            if (osVersion != null && !osVersion.equals("")) {
//                operatingSystem.setOsVersion(osVersion);
//            }
//            operatingSystems.add(operatingSystem);
//        }
        return operatingSystems;
    }

}
