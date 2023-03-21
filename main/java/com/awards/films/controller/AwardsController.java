package com.awards.films.controller;

import com.awards.films.service.AwardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/raspberry", produces = MediaType.APPLICATION_JSON_VALUE)
public class AwardsController {

    @Autowired
    private AwardsService service;

    @GetMapping("/")
    public ResponseEntity<?> getAwards() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAwards());
    }

}
