package com.uwv.spring_boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uwv.spring_boot.service.SofiCheckService1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean(value="sofis1")
    public SofiCheckService1 sofiCheckService1(){
        return new SofiCheckService1();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
