package com.uwv.spring_boot.service;

import com.uwv.spring_boot.model.Message;
import com.uwv.spring_boot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
