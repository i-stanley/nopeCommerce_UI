package org.nopeCommerce_ui.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Credentials {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Credentials.class
                .getClassLoader()
                .getResourceAsStream("test.properties")) {

            if (input != null) {
                properties.load(input);
            }

        } catch (IOException e) {
            throw new RuntimeException("Cannot load test.properties", e);
        }
    }

    private Credentials() {

    }

    public static String email() {
        return System.getProperty(
                "user",
                properties.getProperty("email")
        );
    }

    public static String password() {
        return System.getProperty(
                "pass",
                properties.getProperty("password")
        );
    }
}
