package org.dajo.configuration;

import java.util.Properties;

public final class ConfigurationReader {

    static public ConfigAccessor loadExternalProperties(final String... externalPropertiesFilenames) {
        final Properties properties = new Properties();
        for(int i=0; i < externalPropertiesFilenames.length; ++i) {
            String currentPropertyFilename = externalPropertiesFilenames[i];
            PropertiesLoader.loadExternalProperties(properties, currentPropertyFilename);
        }
        ConfigurationAccessorImpl config = new ConfigurationAccessorImpl(properties);
        return config;
    }

    static public ConfigAccessor loadInternalProperties(final String... externalPropertiesFilenames) {
        final Properties properties = new Properties();
        for(int i=0; i < externalPropertiesFilenames.length; ++i) {
            String currentPropertyFilename = externalPropertiesFilenames[i];
            PropertiesLoader.loadInternalClasspathProperties(PropertiesLoader.class.getClassLoader(), properties, currentPropertyFilename);
        }
        ConfigurationAccessorImpl config = new ConfigurationAccessorImpl(properties);
        return config;
    }

    static public ConfigAccessor loadInternalProperties(final ClassLoader classLoader, final String... externalPropertiesFilenames) {
        final Properties properties = new Properties();
        for(int i=0; i < externalPropertiesFilenames.length; ++i) {
            String currentPropertyFilename = externalPropertiesFilenames[i];
            PropertiesLoader.loadInternalClasspathProperties(classLoader, properties, currentPropertyFilename);
        }
        ConfigurationAccessorImpl config = new ConfigurationAccessorImpl(properties);
        return config;
    }

}// class
