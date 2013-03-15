package org.dajo.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertiesLoader {

    static private final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);

    static void loadInternalClasspathProperties(final ClassLoader classLoader, final Properties properties, final String filename) {
        InputStream in = null;
        try {
            in = classLoader.getResourceAsStream(filename);
            if( in != null ) {
                properties.load(in);
            }
            else {
                throw new RuntimeException("Resource not found. filename="+filename);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Could no load properties. filename="+filename, e);
        }
        finally {
            if(in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    LOGGER.error("Could not close the file.", e);
                }
            }
        }
    }

    static void loadExternalProperties(final Properties properties, final String filename) {
        FileInputStream in = null;
        try {
            File file = new File(filename);
            LOGGER.info("Loading properties. file=" + file.getAbsoluteFile());
            in = new FileInputStream(filename);
            properties.load(in);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("Resource not found. filename="+filename, e);
        }
        catch (IOException e) {
            throw new RuntimeException("Could no load properties. filename="+filename, e);
        }
        finally {
            if(in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    LOGGER.error("Could not close the file. filename="+filename, e);
                }
            }
        }
    }

}// class