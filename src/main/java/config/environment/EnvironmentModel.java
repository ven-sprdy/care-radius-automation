package config.environment;

public class EnvironmentModel {

    private String envName;

    public EnvironmentModel(String envName) {
        this.envName = envName;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

}
