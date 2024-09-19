package com.uwv.spring_boot.service;

import org.springframework.stereotype.Service;

@Service(value="sofis2")
public class SofiCheckService2 {

    public boolean sofiChecker( String sofinummer){
        return sofinummer.length() == 9;
    }

}

