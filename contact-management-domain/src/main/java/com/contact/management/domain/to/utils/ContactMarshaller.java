package com.contact.management.domain.to.utils;
/*
 * Created by alysonlv - 2019-03-02
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import java.io.IOException;
import java.util.Optional;

public class ContactMarshaller {

    private static ObjectMapper mapper;

    static {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.registerModule(new GuavaModule());
        }
    }

    private ContactMarshaller() {
    }

    public static Optional<Object> unmarshal(Optional<String> json, Class clazz) {
        try {
            if (json.isPresent()) {
                return Optional.ofNullable(mapper.readValue(json.get(), clazz));
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }

    public static Optional<String> marshal(Object o) {
        try {
            return Optional.ofNullable(mapper.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            return Optional.empty();

        }
    }
}
