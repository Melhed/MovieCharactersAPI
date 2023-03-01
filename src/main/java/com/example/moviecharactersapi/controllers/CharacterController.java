package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.dto.CharacterDTO;
import com.example.moviecharactersapi.mappers.CharacterMapper;
import com.example.moviecharactersapi.models.dto.MovieGetDTO;
import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Movie;
import com.example.moviecharactersapi.services.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/characters")
public class CharacterController {


    private final CharacterService characterService;
    private final CharacterMapper characterMapper;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Character does not exist with supplied ID",
                    content = @Content),
    })
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Get all characters")
    public List<CharacterDTO> getAllCharacters() {
        List<Character> characterList = characterService.findAll().stream().toList();
        return characterList.stream().map(characterMapper::dtoToCharacter).toList();
    }


    @Operation(summary = "Get Characters by movie ID")
    @GetMapping("/movie/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<CharacterDTO> getCharactersByMovieId(@PathVariable Integer id) {
        Set<Character> characterList = characterService.findByMovieId(id);
        return characterList.stream().map(characterMapper::dtoToCharacter).collect(Collectors.toSet());
    }

    @Operation(summary = "Get Characters by franchise ID")
    @GetMapping("/franchise/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<CharacterDTO> getCharactersByFranchiseId(@PathVariable Integer id) {
        Set<Character> characterList = characterService.findByFranchiseId(id);
        return characterList.stream().map(characterMapper::dtoToCharacter).collect(Collectors.toSet());
    }

    @Operation(summary = "Get a character by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Character does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))})
    })
    @GetMapping("{id}") // GET: localhost:8080/api/v1/characters/1
    @ResponseStatus(value = HttpStatus.OK)
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


    @Operation(summary = "Adds new Character")
    @PostMapping // POST: localhost:8080/api/v1/characters
    public ResponseEntity add(@RequestBody CharacterDTO characterDTO) {

        Character character = characterMapper.toCharacter(characterDTO);
        Character newCharacter = characterService.add(character);
        URI location = URI.create("characters/" + newCharacter.getId());
        return ResponseEntity.created(location).build();

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

        if (id != characterDTO.getId()) {
            return ResponseEntity.badRequest().build();
        }

        characterService.update(characterMapper.toCharacter(characterDTO));

        return ResponseEntity.ok().build();
    }



}
