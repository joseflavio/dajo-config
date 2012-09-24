package org.dajo.framework.adapters;


public final class DajoFilepath {

    private final Type type;
    private final String filepath;

    public DajoFilepath(final Type type, final String filepath) {
        this.type = type;
        this.filepath = filepath;
    }

    public Type getType() {
        return type;
    }

    public String getFilepath() {
        return filepath;
    }

    @Override
    public String toString() {
        return "FilepathVO [type=" + type + ", filepath=" + filepath + "]";
    }

    public static enum Type {
        CLASS_PATH, FILESYSTEM
    }

}// class
