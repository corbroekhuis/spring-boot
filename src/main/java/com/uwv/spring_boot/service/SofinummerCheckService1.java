package com.uwv.spring_boot.service;

public class SofinummerCheckService1 {

    public boolean checkSofinummer(String sofinummer) {
        return sofinummer.length() == 9;
    }
}
