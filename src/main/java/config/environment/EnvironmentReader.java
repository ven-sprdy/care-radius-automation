package config.environment;

import interfaces.Reader;

import java.util.ArrayList;
import java.util.Collection;

public class EnvironmentReader implements Reader<EnvironmentModel> {

    @Override
    public Collection<EnvironmentModel> read() {
        Collection<EnvironmentModel> environment = new ArrayList<>();
        String[] envs = System.getProperty("env").replace(" ", "").split(",");

        for (String env : envs) {
            environment.add(new EnvironmentModel(env));
        }

        return  environment;
    }

}
