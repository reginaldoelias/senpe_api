package br.mil.mar.saudenaval.senpe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ConnectionController {

    @GetMapping
    public ResponseEntity<String> getText(){
        return ResponseEntity.ok().body("Connection GET successfully");
    }

    @PostMapping("/post")
    public ResponseEntity<String> getPost(){
        return ResponseEntity.ok().body("Connection POST successfully");
    }

}
