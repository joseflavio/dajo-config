package org.dajo.types;


public interface TypeAdapter<T, K> {

    TypeAdapterResult<T> adapt(K value);

}
