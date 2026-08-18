package com.library.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream(".env")) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println(".env faylı oxunarkən xəta baş verdi!");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}