package com.uwv.spring_boot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uwv.spring_boot.model.Message;
import com.uwv.spring_boot.service.MessageService;
import com.uwv.spring_boot.service.SofiCheckService1;
import com.uwv.spring_boot.service.SofiCheckService2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "API for message exchange",
        description = "This API offers all services needed to handle message exchange")
public class Controller {

    private MessageService messageService;
    private SofiCheckService1 sofiCheckService1;
    private SofiCheckService2 sofiCheckService2;
    private ObjectMapper objectMapper;

    @Autowired
    public Controller(
            MessageService messageService,
            SofiCheckService1 sofiCheckService1,
            SofiCheckService2 sofiCheckService2,
            ObjectMapper objectMapper){
        this.messageService = messageService;
        this.sofiCheckService1 = sofiCheckService1;
        this.sofiCheckService2 = sofiCheckService2;
        this.objectMapper = objectMapper;
    }

    // http://localhost:8080/api/helloworld
    @GetMapping("helloworld")
    @Operation(summary = "Onzin methode die niet wordt gebruikt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message is returned")
    })
    public String getMessage(@RequestParam String message){
        return message;
    }

    // http://localhost:8080/api/helloworld/532375ed-306a-430a-939c-7e5537e7abdd
    @GetMapping("helloworld/{uuid}")
    @Operation(summary = "Vindt message op basis van id (UUID) Vindt message op basis van id (UUID) Vindt message op basis van id (UUID) Vindt message op basis van id (UUID) Vindt message op basis van id (UUID) Vindt message op basis van id (UUID) Vindt message op basis van id (UUID) Vindt message op basis van id (UUID) Vindt message op basis van id (UUID) Vindt message op basis van id (UUID)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message is gevonden"),
            @ApiResponse(responseCode = "404", description = "De message is niet gevonden", content = @Content),
    })
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
    public ResponseEntity<Message> getMessageBody(@RequestBody Message message) throws JsonProcessingException {
        System.out.println( message);

        Message saved = messageService.save( message);

        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(saved);
        System.out.println( "Formatted: " + json);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping(value="helloworld/{uuid}")
    public String deleteMessage(@PathVariable UUID uuid) {

        messageService.deleteById( uuid);
        return "deleted";
    }
}
