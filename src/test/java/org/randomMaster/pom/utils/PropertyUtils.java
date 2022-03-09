package org.randomMaster.pom.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

    public static Properties PropertyLoader(String filePath) {
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(filePath);
            try {
                properties.load(inputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                throw new RuntimeException("failed to load properties file " + filePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("properties file not found" + filePath);
        }
        return properties;
    }
}