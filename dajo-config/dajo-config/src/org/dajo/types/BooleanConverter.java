package org.dajo.types;

public class BooleanConverter {

    static public boolean checkedIntToBool(final int value) {
        if (value == 0) {
            return false;
        }
        else if (value == 1) {
            return true;
        }
        else {
            throw new RuntimeException("Invalid value. value=" + value);
        }
    }
    
}
