package com.uwv.spring_boot.controller;

import com.uwv.spring_boot.model.Message;
import com.uwv.spring_boot.service.MessageService;
import com.uwv.spring_boot.service.SofiCheckService1;
import com.uwv.spring_boot.service.SofiCheckService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class Controller {

    private MessageService messageService;
    private SofiCheckService1 sofiCheckService1;
    private SofiCheckService2 sofiCheckService2;

    @Autowired
    public Controller(
            MessageService messageService,
            SofiCheckService1 sofiCheckService1,
            SofiCheckService2 sofiCheckService2){
        this.messageService = messageService;
        this.sofiCheckService1 = sofiCheckService1;
        this.sofiCheckService2 = sofiCheckService2;
    }

    // http://localhost:8080/api/helloworld
    @GetMapping("helloworld")
    public String getMessage(@RequestParam String message){
        return message;
    }

    // http://localhost:8080/api/helloworld/532375ed-306a-430a-939c-7e5537e7abdd
    @GetMapping("helloworld/{uuid}")
    public ResponseEntity<Message> getMessageById(@PathVariable UUID uuid){
        Optional<Message> message =  messageService.findById( uuid);

        if(message.isPresent()){
            return ResponseEntity.ok(message.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // http://localhost:8080/api/message
    @GetMapping(value = "message", produces = "application/json")
    public ResponseEntity<Iterable<Message>> getMessages(){

        Iterable<Message> messages = messageService.findAll();

        return ResponseEntity.ok(messages);
    }

    // http://localhost:8080/api/helloworld
    @PostMapping(value = "helloworld", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Message> getMessageBody(@RequestBody Message message){
        System.out.println( message);

        messageService.save( message);

        return ResponseEntity.ok(message);
    }

    @DeleteMapping(value="helloworld/{uuid}")
    public String deleteMessage(@PathVariable UUID uuid) {

        messageService.deleteById( uuid);
        return "deleted";
    }
}
