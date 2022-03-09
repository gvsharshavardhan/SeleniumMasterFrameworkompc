package org.randomMaster.pom.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public final class JacksonUtils {

    public static <T> T deserializeJson(String jsonFile, Class<T> T) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(jsonFile);
        return objectMapper.readValue(is, T);
    }
}