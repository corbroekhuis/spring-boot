package com.uwv.spring_boot.service;

import com.uwv.spring_boot.model.Message;

public interface MessageService {
    Iterable<Message> findAll();
}
