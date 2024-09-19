package com.uwv.spring_boot.controller;

import com.uwv.spring_boot.model.Message;
import com.uwv.spring_boot.service.MessageService;
import com.uwv.spring_boot.service.SofinummerCheckService1;
import com.uwv.spring_boot.service.SofinummerCheckService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    private MessageService messageService;
    SofinummerCheckService1 sofinummerCheckService1;
    SofinummerCheckService2 sofinummerCheckService2;

    @Autowired
    public Controller(
            MessageService messageService,
            SofinummerCheckService1 sofinummerCheckService1,
            SofinummerCheckService2 sofinummerCheckService2){
        this.messageService = messageService;
        this.sofinummerCheckService1 = sofinummerCheckService1;
        this.sofinummerCheckService2 = sofinummerCheckService2;
    }
    // http://localhost:8080/api/helloworld
    @GetMapping("helloworld")
    public String getMessage(@RequestParam String message){
        return message;
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
                return ResponseEntity.ok(message);
    }

}
