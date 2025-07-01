package br.mil.mar.saudenaval.senpe.controller;


import br.mil.mar.saudenaval.senpe.services.vitrines.VitrinesService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vitrines")
public class VitrinesController {

    @Autowired
    private VitrinesService services;

    @GetMapping
    public ResponseEntity<String> load(){

        return  services.loadVitrines();
    }
}
