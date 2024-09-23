package com.uwv.spring_boot.service.qualifier;

import com.uwv.spring_boot.model.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Qualifier("mssql.datasource")
public class MsSqlDataSource implements DataSource{

    @Override
    public Iterable<Message> findAll(){
        Message[] messages = { new Message("SqlServer is great!!!"), new Message("SqlServer is great!!!")};
        return Arrays.asList( messages);
    }
}
