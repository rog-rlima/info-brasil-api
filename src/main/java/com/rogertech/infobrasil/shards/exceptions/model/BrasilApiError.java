package com.rogertech.infobrasil.shards.exceptions.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record BrasilApiError(String message,
                             String type,
                             String name) {


    public static BrasilApiError FromDTO(String message, ObjectMapper objectMapper) {
        try {
            int startIndex = message.lastIndexOf("[") + 1;
            int endIndex = message.lastIndexOf("]");
            String jsonString = message.substring(startIndex, endIndex);
            return objectMapper.readValue(jsonString, BrasilApiError.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}