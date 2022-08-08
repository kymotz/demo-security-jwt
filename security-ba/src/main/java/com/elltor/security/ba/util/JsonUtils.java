package com.elltor.security.ba.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    public static final ObjectMapper om = new ObjectMapper();

    public static String writeAsString(Object obj) {
        if (obj == null) return null;
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 序列号出错", e);
        }
    }
}
