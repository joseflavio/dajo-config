package org.dajo.configuration;

import java.util.Properties;

public final class ConfigurationReader {

    public static ConfigAccessor loadExternalProperties(final String... externalPropertiesFilenames) {
        final Properties properties = new Properties();
        for (int i = 0; i < externalPropertiesFilenames.length; ++i) {
            String currentPropertyFilename = externalPropertiesFilenames[i];
            PropertiesLoader.loadExternalPropertiesFile(properties, currentPropertyFilename);
        }
        final PropertiesConfigAccessor config = new PropertiesConfigAccessor(properties);
        return config;
    }

    public static ConfigAccessor loadInternalProperties(final String... classpathPropertiesFilenames) {
        final Properties properties = new Properties();
        for (int i = 0; i < classpathPropertiesFilenames.length; ++i) {
            String currentPropertyFilename = classpathPropertiesFilenames[i];
            PropertiesLoader.loadClasspathPropertiesFile(properties, currentPropertyFilename);
        }
        final PropertiesConfigAccessor config = new PropertiesConfigAccessor(properties);
        return config;
    }

    public static ConfigAccessor loadInternalProperties(final ClassLoader classLoader, final String... classpathPropertiesFilenames) {
        final Properties properties = new Properties();
        for (int i = 0; i < classpathPropertiesFilenames.length; ++i) {
            String currentPropertyFilename = classpathPropertiesFilenames[i];
            PropertiesLoader.loadClasspathPropertiesFile(classLoader, properties, currentPropertyFilename);
        }
        final PropertiesConfigAccessor config = new PropertiesConfigAccessor(properties);
        return config;
    }

}// class
