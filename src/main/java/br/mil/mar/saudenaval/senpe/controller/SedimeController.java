package br.mil.mar.saudenaval.senpe.controller;


import br.mil.mar.saudenaval.senpe.entities.Sedime;
import br.mil.mar.saudenaval.senpe.services.sedime.SedimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sedime")
public class SedimeController {

    @Autowired
    private SedimeService service;

    @GetMapping
    public ResponseEntity<List<Sedime>> getSedime(){
        List<Sedime> list = service.getData();
        return ResponseEntity.ok().body(list);
    }

}
