package com.example.moviecharactersapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {


    @GetMapping("foo") // GET: http://localhost:8080/foo
    public ResponseEntity<String> foo() {


        return ResponseEntity.ok("Bar!");
    }
}
