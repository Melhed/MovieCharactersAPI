package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.dto.CharacterDTO;
import com.example.moviecharactersapi.models.dto.CharacterMapper;
import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.services.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/characters")
public class CharacterController {


    private final CharacterService characterService;

    private final CharacterMapper characterMapper;





    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Get all characters")
    public List<CharacterDTO> getAllCharacters() {
        List<Character> characterList = characterService.findAll();
        return characterList.stream().map(characterMapper::dtoToCharacter).toList();
    }

/*
    @Operation(summary = "Adds a new character to the database")
    @PostMapping // POST: localhost:8080/api/v1/characters/add

    public ResponseEntity<Character> add(@RequestBody Character character) {
        Character newCharacter = characterService.add(character);

        URI location = URI.create("characters/" + newCharacter.getId());
        return ResponseEntity.created(location).build();
    }
*/

    @GetMapping("{id}") // GET: localhost:8080/api/v1/characters/1
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Get a character by ID")
    public ResponseEntity getById(@PathVariable Integer id) {
        CharacterDTO character = characterMapper.dtoToCharacter(characterService.findById(id));
        return ResponseEntity.ok(character);
    }

    @GetMapping("/name/{name}") // GET: localhost:8080/api/v1/characters/name
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Get a character by name")
    public Set<CharacterDTO> getByName(@PathVariable String name) {
        Set<Character> characterList = characterService.findByNameContainsIgnoreCase(name);
        return characterList.stream().map(characterMapper::dtoToCharacter).collect(Collectors.toSet());
    }

/*
    @PostMapping("/add") // POST: localhost:8080/api/v1/characters/add
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Adds a new character to the database")
    public CharacterDTO addCharacter(@RequestBody Character character) {
        Character newCharacter = characterService.add(character);
        return characterMapper.dtoToCharacter(newCharacter);
    }
*/



    @Operation(summary = "Adds new Student")
    @PostMapping // POST: localhost:8080/api/v1/characters
    public ResponseEntity add(@RequestBody CharacterDTO characterDTO) {

        Character character = characterMapper.toCharacter(characterDTO);
        Character newCharacter = characterService.add(character);
        URI location = URI.create("characters/" + newCharacter.getId());
        return ResponseEntity.created(location).build();
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/characters/1
    @Operation(summary = "Delete a character by ID")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        characterService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("{id}") // PUT: localhost:8080/api/v1/characters/1
    @Operation(summary = "Update a character by ID")
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody CharacterDTO characterDTO) {

        if(id != characterDTO.getId()) {
            return ResponseEntity.badRequest().build();
        }


        characterService.update(characterMapper.toCharacter(characterDTO));

        return ResponseEntity.ok().build();
    }


/*    @PostMapping // POST: localhost:8080/api/v1/characters/add
    public CharacterDTO addCharacter(@RequestBody CharacterDTO characterDTO) {
        Character character = characterMapper.toCharacter(characterDTO);
        Character newCharacter = characterService.add(character);
        return characterMapper.dtoToCharacter(newCharacter);
    }*/

}
