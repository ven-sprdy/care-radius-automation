package com.parabank.config.environment;

import com.parabank.config.properties.PropertiesConfigReader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvironmentUrlReader {

    private final PropertiesConfigReader propertiesConfigReader = new PropertiesConfigReader();
    private String envName;

    public EnvironmentUrlReader(String envName) {
        this.envName = propertiesConfigReader.getProperty(String.format("%s.url", envName));
    }

}
