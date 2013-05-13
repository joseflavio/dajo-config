package org.dajo.configuration;

import java.util.Map;

import org.dajo.types.Function;
import org.dajo.types.Optional;

public interface ConfigAccessor {

    Map<String, String> getAvailableProperties();

    String getMandatoryProperty(String propertyName);

    <T> T getMandatoryProperty(String propertyName, Function<String, Optional<T>> adapter);

    String getOptionalProperty(String propertyName, String defaultValue);

    <T> T getOptionalProperty(String propertyName, Function<String, Optional<T>> adapter, T defaultValue);

}// interface
