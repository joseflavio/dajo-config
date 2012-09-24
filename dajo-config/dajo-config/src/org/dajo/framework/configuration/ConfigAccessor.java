package org.dajo.framework.configuration;

import java.util.Map;

import org.dajo.framework.adapters.TypeAdapter;

public interface ConfigAccessor {

    Map<String, String> getAvailableProperties();

    String getMandatoryProperty(String propertyName);

    <T> T getMandatoryProperty(String propertyName, TypeAdapter<T, String> adapter);

    String getOptionalProperty(String propertyName, String defaultValue);

    <T> T getOptionalProperty(String propertyName, TypeAdapter<T, String> adapter, T defaultValue);

}// interface
