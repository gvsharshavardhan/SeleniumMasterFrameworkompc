package org.randomMaster.pom.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;

    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.PropertyLoader("./src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null)
            configLoader = new ConfigLoader();
        return configLoader;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) return prop;
        else throw new RuntimeException("Baseurl is not specified in the properties file!!");
    }

    public String getUserId() {
        String prop = properties.getProperty("userid");
        if (prop != null) return prop;
        else throw new RuntimeException("user id is not specified in the properties file!!");
    }
}