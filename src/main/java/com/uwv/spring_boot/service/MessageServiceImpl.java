package com.uwv.spring_boot.service;

import com.uwv.spring_boot.model.Message;
import com.uwv.spring_boot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService{

    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Override
    public Iterable<Message> findAll(){
        return messageRepository.findAll();
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save( message);
    }

    @Override
    public Optional<Message> findById(UUID uuid) {
        return messageRepository.findById( uuid);
    }

    @Override
    public void deleteById(UUID uuid) {
        messageRepository.deleteById( uuid);
    }

    @Override
    public Optional<Message> findByMessage(String message) {
        return messageRepository.findByMessage(message);
    }
}
