package com.edunetcracker.simulator.rest;

import com.edunetcracker.simulator.model.DBObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class RestControllerImpl implements RestController {

    private static ObjectMapper mapper;

    RestControllerImpl() {
        mapper = new ObjectMapper();
        mapper.setVisibilityChecker(mapper.getDeserializationConfig().getDefaultVisibilityChecker()
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE)
                .withFieldVisibility(JsonAutoDetect.Visibility.NONE)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE));
    }

    public String toJson(DBObject dbObject) {
        String result = null;
        try {
            result = mapper.writeValueAsString(dbObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
