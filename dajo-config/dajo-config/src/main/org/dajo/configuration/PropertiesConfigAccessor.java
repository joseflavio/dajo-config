package org.dajo.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.dajo.types.ValidatedReturn;
import org.dajo.types.adapter.TypeAdapter;

public final class PropertiesConfigAccessor implements ConfigAccessor {

    private final Properties properties;

    public PropertiesConfigAccessor(final Properties properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, String> getAvailableProperties() {
        Map<String, String> availableProperties = new HashMap<String, String>();
        Set<String> propertyNames = properties.stringPropertyNames();
        for (String propertyName : propertyNames) {
            String propertyValue = properties.getProperty(propertyName);
            availableProperties.put(propertyName, propertyValue);
        }
        return availableProperties;
    }

    @Override
    public String getMandatoryProperty(final String propertyName) {
        final String propertyValue = properties.getProperty(propertyName);
        if (propertyValue == null) {
            throw new RuntimeException("Missing mandatory property. propertyName=" + propertyName);
        } else {
            return propertyValue;
        }
    }

    @Override
    public String getOptionalProperty(final String propertyName, final String defaultValue) {
        final String propertyValue = properties.getProperty(propertyName);
        if (propertyValue == null) {
            return defaultValue;
        } else {
            return propertyValue;
        }
    }

    @Override
    public <T> T getMandatoryProperty(final String propertyName, final TypeAdapter<T, String> adapter) {
        final String value = properties.getProperty(propertyName);
        if (value == null) {
            throw new RuntimeException("Missing mandatory property. propertyName=" + propertyName);
        } else {
            ValidatedReturn<T> adapterResult = adapter.adapt(value);
            if (adapterResult.isValid() == false) {
                throw new RuntimeException("Invalid value for mandatory property. propertyName=" + propertyName + ", propertyValue=" + value);
            }
            return adapterResult.value();
        }
    }

    @Override
    public <T> T getOptionalProperty(final String propertyName, final TypeAdapter<T, String> adapter, final T defaultValue) {
        final String propertyValue = properties.getProperty(propertyName);
        final ValidatedReturn<T> adapterResult = adapter.adapt(propertyValue);
        if (adapterResult.isValid() == false) {
            return defaultValue;
        }
        return adapterResult.value();
    }

}// class
