package org.dajo.configuration;

import java.util.HashMap;
import java.util.Map;

import org.dajo.types.Function;
import org.dajo.types.Optional;

public final class SimpleConfigAccessor implements ConfigAccessor {

    static public SimpleConfigAccessor getInstance(final Map<String, String> map) {
        return new SimpleConfigAccessor(map);
    }

    private final Map<String, String> map = new HashMap<String, String>();

    private SimpleConfigAccessor(final Map<String, String> givenMap) {
        map.putAll(givenMap);
    }

    @Override
    public Map<String, String> getAvailableProperties() {
        Map<String, String> result = new HashMap<String, String>();
        result.putAll(map);
        return result;
    }

    @Override
    public String getMandatoryProperty(final String propertyName) {
        String value = map.get(propertyName);
        if (value == null) {
            throw new RuntimeException("Missing mandatory property: " + propertyName);
        }
        return value;
    }

    @Override
    public <T> T getMandatoryProperty(final String propertyName, final Function<String, Optional<T>> adapter) {
        final String value = map.get(propertyName);
        if (value == null) {
            throw new RuntimeException("Missing mandatory property: " + propertyName);
        }
        final Optional<T> adapterResult = adapter.apply(value);
        if (adapterResult.isPresent() == false) {
            throw new RuntimeException("Invalid value for mandatory property. propertyName=" + propertyName + ", value=" + value);
        }
        return adapterResult.get();
    }

    @Override
    public String getOptionalProperty(final String propertyName, final String defaultValue) {
        final String propertyValue = map.get(propertyName);
        if (propertyValue == null) {
            return defaultValue;
        } else {
            return propertyValue;
        }
    }

    @Override
    public <T> T getOptionalProperty(final String propertyName, final Function<String, Optional<T>> adapter, final T defaultValue) {
        final String propertyValue = map.get(propertyName);
        final Optional<T> adapterResult = adapter.apply(propertyValue);
        if (adapterResult.isPresent() == false) {
            return defaultValue;
        }
        return adapterResult.get();
    }

}// class
