package com.step.ivko.config;

import com.step.ivko.exception.ApplicationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    private static final String PROPERTY_FILE_NAME =
            "application.properties";
    private static PropertiesManager Instance;
    private Properties properties;

    private PropertiesManager() {
    }

    public static PropertiesManager getInstance() {
        if (Instance == null) {
            Instance = new PropertiesManager();
        }
        return Instance;
    }

    public Properties getApplicationProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream stream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(PROPERTY_FILE_NAME)) {
                properties.load(stream);
            } catch (IOException e) {
                throw new ApplicationException("Failed to load property file", e);
            }
        }
        return properties;
    }
}