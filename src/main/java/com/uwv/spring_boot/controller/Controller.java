package com.uwv.spring_boot.controller;

import com.uwv.spring_boot.component.PrettyObjectMapper;
import com.uwv.spring_boot.model.Message;
import com.uwv.spring_boot.service.MessageService;
import com.uwv.spring_boot.service.SofiCheckService1;
import com.uwv.spring_boot.service.SofiCheckService2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
        description = "This API offers all <br>services needed to handle <br>message exchange")
public class Controller {

    private MessageService messageService;
    private SofiCheckService1 sofiCheckService1;
    private SofiCheckService2 sofiCheckService2;
    private PrettyObjectMapper objectMapper;

    @Autowired
    public Controller(
            MessageService messageService,
            SofiCheckService1 sofiCheckService1,
            SofiCheckService2 sofiCheckService2,
            PrettyObjectMapper objectMapper){
        this.messageService = messageService;
        this.sofiCheckService1 = sofiCheckService1;
        this.sofiCheckService2 = sofiCheckService2;
        this.objectMapper = objectMapper;
    }

    // http://localhost:8080/api/helloworld?message=Hello%20World
    @GetMapping("helloworld")
    @Operation(summary = "Deze service kan in de toekomst worden verwijderd. Gebruik als alternatief: GET api/message/<id>", deprecated = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message is returned")
    })
    public String getMessage(@RequestParam String message){
        return message;
    }


    // http://localhost:8080/api/message/532375ed-306a-430a-939c-7e5537e7abdd
    @GetMapping("message/{uuid}")
    @Operation(summary = "Vindt message op basis van id (UUID)",
               description= "<ul><li>Vindt message op basis van id (UUID)</li><li> Vindt message op basis van id (UUID)</li><li> Vindt message op basis van id (UUID) </li><li>Vindt message op basis van id (UUID)</li><li>Vindt message op basis van id (UUID)</li><li> Vindt message op basis van id (UUID) </li><li>Vindt message op basis van id (UUID) </li><li>Vindt message op basis van id (UUID) </li><li>Vindt message op basis van id (UUID) </li><li>Vindt message op basis van id (UUID)</li></ul>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bericht gevonden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class),
                            examples = {
                                    @ExampleObject(
                                            //language=JSON
                                            value = """
                                                    {
                                                      "id": "9675f79c-f23f-4fe6-8ecd-f56cf57b8c57",
                                                      "BERICHT": "Dit is het tweede bericht"
                                                    }       
                                                    """
                                    )
                            }),
                    }),
            @ApiResponse(responseCode = "404", description = "Bericht niet gevonden",
                    content = @Content) })
    public ResponseEntity<Message> getMessageById(@PathVariable UUID uuid){
        Optional<Message> message =  messageService.findById( uuid);

        if(message.isPresent()){
            return ResponseEntity.ok(message.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    // http://localhost:8080/api/messagebymessage?message=Dit%20is%20de%20eerste%20mededeling
    @GetMapping("messagebymessage")
    public ResponseEntity<Message> getMessageByMessage(@RequestParam String message){
        Optional<Message> messageFound =  messageService.findByMessage( message);

        if(messageFound.isPresent()){
            return ResponseEntity.ok(messageFound.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    // http://localhost:8080/api/message
    @GetMapping(value = "message", produces = "application/json")
    public ResponseEntity<Iterable<Message>> getMessages(){

        Iterable<Message> messages = messageService.findAll();
        return ResponseEntity.ok(messages);
    }

    // http://localhost:8080/api/message
    @PostMapping(value = "message", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        System.out.println( message);

        Message saved = messageService.save( message);

        String json = objectMapper.writeValueAsString(saved);
        System.out.println( "Formatted: " + json);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping(value="message/{uuid}")
    public String deleteMessage(@PathVariable UUID uuid) {

        messageService.deleteById( uuid);
        return "deleted";
    }
}
