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

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);

    public static Properties loadClasspathPropertiesFile(final String propertyFilename) {
        final Properties properties = new Properties();
        readClasspathFile(PropertiesLoader.class.getClassLoader(), new PropertiesInputStreamLoader(properties), propertyFilename);
        return properties;
    }

    public static void loadClasspathPropertiesFile(final Properties properties, final String propertyFilename) {
        readClasspathFile(PropertiesLoader.class.getClassLoader(), new PropertiesInputStreamLoader(properties), propertyFilename);
    }

    public static void loadClasspathPropertiesFile(final ClassLoader classLoader, final Properties properties, final String propertyFilename) {
        readClasspathFile(classLoader, new PropertiesInputStreamLoader(properties), propertyFilename);
    }

    public static void loadClasspathResource(final InputStreamLoader loader, final String propertyFilename) {
        readClasspathFile(PropertiesLoader.class.getClassLoader(), loader, propertyFilename);
    }

    private static void readClasspathFile(final ClassLoader classLoader, final InputStreamLoader loader, final String filename) {
        InputStream in = null;
        try {
            in = classLoader.getResourceAsStream(filename);
            if (in != null) {
                loader.loadFromInputStream(in);
            } else {
                throw new RuntimeException("Resource not found. filename=" + filename);
            }
        } catch (final Exception e) {
            throw new RuntimeException("Unexpected. e=" + e + ", filename=" + filename, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error("Could not close the file.", e);
                }
            }
        }
    }

    public interface InputStreamLoader {
        void loadFromInputStream(InputStream input) throws Exception;
    }

    public static class PropertiesInputStreamLoader implements InputStreamLoader {
        private final Properties properties;

        public PropertiesInputStreamLoader(final Properties properties) {
            this.properties = properties;
        }

        @Override
        public void loadFromInputStream(final InputStream input) throws Exception {
            properties.load(input);
        }
    }

    static void loadExternalPropertiesFile(final Properties properties, final String filename) {
        FileInputStream in = null;
        try {
            File file = new File(filename);
            LOGGER.info("Loading properties. file=" + file.getAbsoluteFile());
            in = new FileInputStream(filename);
            properties.load(in);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Resource not found. filename=" + filename, e);
        } catch (IOException e) {
            throw new RuntimeException("Could no load properties. filename=" + filename, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error("Could not close the file. filename=" + filename, e);
                }
            }
        }
    }

}// class
