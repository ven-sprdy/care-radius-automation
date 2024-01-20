package config.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyConfigReader {

    protected String configFilePath;

    public PropertyConfigReader(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public String getProperty(String propertyName) {
        String property = System.getProperty(propertyName);

        if (property == null) {
            loadProperties();
            property = System.getProperty(propertyName);
        }

        return  property;
    }

    protected Properties loadProperties() {
        Properties properties = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream(this.configFilePath);
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        Properties systemProperties = System.getProperties();

        Enumeration loadPropertyNames = properties.propertyNames();

        while (loadPropertyNames.hasMoreElements()) {
            String name = loadPropertyNames.nextElement().toString();

            systemProperties.setProperty(name, properties.getProperty(name));
        }
        return properties;
    }

}
