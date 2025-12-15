package org.nopeCommerce_ui.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Credentials {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Credentials.class
                .getClassLoader()
                .getResourceAsStream("test.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot load test.properties");
        }
    }

    public static String email() {
        return properties.getProperty("email");
    }

    public static String password() {
        return properties.getProperty("password");
    }
}
