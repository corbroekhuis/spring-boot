package com.uwv.spring_boot.controller;

import com.uwv.spring_boot.model.Message;
import com.uwv.spring_boot.service.qualifier.DataSource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "API for direct SqlServer database access",
        description = "This API offers all services needed for direct database access, using a SqlServer datasource")
public class MsSqlController {

    DataSource dataSource;

    @Autowired
    public MsSqlController(@Qualifier("mssql.datasource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // http://localhost:8080/api/mssql/message
    @GetMapping(value = "mssql/message", produces = "application/json")
    public ResponseEntity<Iterable<Message>> getMsSqlMessages(){

        Iterable<Message> messages = dataSource.findAll();

        return ResponseEntity.ok(messages);
    }
}
