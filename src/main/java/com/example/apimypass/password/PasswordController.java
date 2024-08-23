package com.example.apimypass.password;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pass")
public class PasswordController {

    @Autowired
    PasswordService service;

    @GetMapping
    public List<Password> getPasswords() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Password> create(@RequestBody Password password, UriComponentsBuilder uriBuilder) {
        service.create(password);

        var uri = uriBuilder
                .path("/users/{id}")
                .buildAndExpand(password.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(password);

    }
}
