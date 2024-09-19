package com.uwv.spring_boot.service;

import org.springframework.stereotype.Service;

@Service("sofivalue")
public class SofinummerCheckService2 {

    public boolean checkSofinummer(String sofinummer) {
        return sofinummer.length() == 9;
    }
}
