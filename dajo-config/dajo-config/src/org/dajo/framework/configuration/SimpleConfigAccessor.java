package org.dajo.framework.configuration;

import java.util.HashMap;
import java.util.Map;

import org.dajo.types.TypeAdapter;
import org.dajo.types.TypeAdapterResult;

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
        if( value == null ) {
            throw new RuntimeException("Missing mandatory property: " + propertyName);
        }
        return value;
    }

    @Override
    public <T> T getMandatoryProperty(final String propertyName, final TypeAdapter<T, String> adapter) {
        String propertyStringValue = map.get(propertyName);
        TypeAdapterResult<T> adapterResult = adapter.adapt(propertyStringValue);
        if( adapterResult.isSuccess() == false ) {
            throw new RuntimeException("Missing mandatory property: " + propertyName);
        }
        return adapterResult.getValue();
    }

    @Override
    public String getOptionalProperty(final String propertyName, final String defaultValue) {
        return null;
    }

    @Override
    public <T> T getOptionalProperty(final String propertyName, final TypeAdapter<T, String> adapter, final T defaultValue) {
        return null;
    }

}// class
