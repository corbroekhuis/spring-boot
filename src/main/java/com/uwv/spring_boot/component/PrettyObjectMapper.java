package com.uwv.spring_boot.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrettyObjectMapper {

    private ObjectMapper objectMapper;

    @Autowired
    public PrettyObjectMapper( ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public String writeValueAsString( Object object){

        String json = "";

        try {

            json = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString( object);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return json;
    }
}
