package com.uwv.spring_boot.config;

import com.uwv.spring_boot.service.SofinummerCheckService1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean("sofichecker")
    public SofinummerCheckService1 sofinummerCheckService1() {
        return new SofinummerCheckService1();
    }
}
