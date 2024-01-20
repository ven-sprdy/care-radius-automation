package config.url;

import config.environment.EnvironmentModel;
import config.environment.EnvironmentReader;
import config.property.PropertyConfigReader;
import interfaces.Reader;

import java.util.ArrayList;
import java.util.Collection;

public class UrlReader implements Reader<UrlModel> {

    protected EnvironmentReader environmentReader;

    public UrlReader() {
        this.environmentReader = new EnvironmentReader();
    }

    @Override
    public Collection<UrlModel> read() {
        Collection<UrlModel> baseUrl = new ArrayList<>();

        for (EnvironmentModel env: environmentReader.read()) {
            UrlModel url = new UrlModel();
            PropertyConfigReader propertyConfigReader = new PropertyConfigReader("src/test/resources/config.properties");
            url.setEnvUrl(propertyConfigReader.getProperty(env.getEnvName() +".url"));
            baseUrl.add(url);
        }

        return baseUrl;
    }

}
