package org.dajo.types;


public final class IntegerAdapter implements TypeAdapter<Integer, String> {

    @Override
    public TypeAdapterResult<Integer> adapt(final String value) {
        try {        
            int intValue = Integer.parseInt(value);
            return new TypeAdapterResult<Integer>(Integer.valueOf(intValue));
        } catch (NumberFormatException e) {
            return new TypeAdapterResult<Integer>();
        }
    }

}// class
