package com.uwv.spring_boot.service;

public class SofiCheckService1 {

    public boolean sofiChecker( String sofinummer){
        return sofinummer.length() == 9;
    }

}
