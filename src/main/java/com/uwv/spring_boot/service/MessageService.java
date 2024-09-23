package com.uwv.spring_boot.service;

import com.uwv.spring_boot.model.Message;

import java.util.Optional;
import java.util.UUID;

public interface MessageService {
    Iterable<Message> findAll();
    Message save( Message message);
    Optional<Message> findById(UUID uuid);
    void deleteById( UUID uuid);
    Optional<Message> findByMessage(String message);
}
