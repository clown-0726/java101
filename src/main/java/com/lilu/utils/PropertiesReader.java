package com.lilu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public static Properties getProperties(String name) {
        Properties prop = new Properties();
        try (InputStream in = PropertiesReader.class.getClassLoader().getResourceAsStream(name)) {
            if (in == null) {
                System.out.println("Sorry, unable to find config.properties");
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            prop.load(in);
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Properties prop = getProperties("kafka.auth.properties");
        System.out.println(prop.getProperty("xxx"));
    }
}
