package com.uwv.spring_boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uwv.spring_boot.model.Message;

import java.util.UUID;

public class Example {

    public static void main(String[] args)  {

        ObjectMapper om = new ObjectMapper();
        Message message = new Message( "Hello");
        message.setId(UUID.randomUUID());

        try {
            String json = om
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString( message);

            System.out.println(json );

             Message m = om.readValue(json, Message.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
