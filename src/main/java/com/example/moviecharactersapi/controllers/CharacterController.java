package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.services.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {


    CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

/*    @GetMapping("foo") // GET: http://localhost:8080/foo
    public ResponseEntity<String> foo() {


        return ResponseEntity.ok(characterService.findAll().toString());
    }*/

    @GetMapping("{id}") // GET: localhost:8080/api/v1/characters/1
    public ResponseEntity<Character> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(characterService.findById(id));
    }

    @GetMapping // GET: localhost:8080/api/v1/characters/findAll
    public ResponseEntity<Collection<Character>> findAll() {
        return ResponseEntity.ok(characterService.findAll());
    }

    @PostMapping // POST: localhost:8080/api/v1/characters/add
    public ResponseEntity<Character> add(@RequestBody Character character) {
        Character newCharacter = characterService.add(character);

        URI location = URI.create("characters/" + newCharacter.getId());
        return ResponseEntity.created(location).build();
    }
}
