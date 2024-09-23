package com.uwv.spring_boot.service.qualifier;

import com.uwv.spring_boot.model.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Qualifier("oracle.datasource")
public class OracleDataSource implements DataSource{

    @Override
    public Iterable<Message> findAll(){
        Message[] messages = { new Message("Oracle is great!!!"), new Message("Oracle is great!!!")};
        return Arrays.asList( messages);
    }
}
