package org.dajo.framework.filepath;

import org.dajo.types.TypeAdapter;
import org.dajo.types.TypeAdapterResult;


public final class DajoFilepathAdapter implements TypeAdapter<DajoFilepath, String> {

    private final DajoFilepath.Type filepathType;

    public DajoFilepathAdapter(final DajoFilepath.Type filepathType) {
        this.filepathType = filepathType;
    }

    @Override
    public TypeAdapterResult<DajoFilepath> adapt(final String value) {
        if( value == null ) {
            return new TypeAdapterResult<DajoFilepath>();
        }
        final DajoFilepath filepathVO = new DajoFilepath(filepathType, value);
        return new TypeAdapterResult<DajoFilepath>(filepathVO);
    }

}// class
