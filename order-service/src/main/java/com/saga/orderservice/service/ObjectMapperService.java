package com.saga.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

@Service
public class ObjectMapperService {

    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public <T> T readStringToObject(String message, Class<T> classType){
        try {
            return message == null || message.trim().isEmpty() ? null : objectMapper.readValue(message, classType);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
