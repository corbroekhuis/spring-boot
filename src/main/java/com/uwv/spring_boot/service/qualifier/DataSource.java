package com.uwv.spring_boot.service.qualifier;

import com.uwv.spring_boot.model.Message;

public interface DataSource {

    Iterable<Message> findAll();
}
