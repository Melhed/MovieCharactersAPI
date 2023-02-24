package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.services.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/character")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController (CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("all")
    public ResponseEntity<Collection<Character>> findAllCharacters() {
        return ResponseEntity.ok(characterService.findAllCharacters());
    }

}
