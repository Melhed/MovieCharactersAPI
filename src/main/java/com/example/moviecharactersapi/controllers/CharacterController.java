package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.dto.CharacterDTO;
import com.example.moviecharactersapi.mappers.CharacterMapper;
import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.services.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {


    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }


    @Operation(summary = "Get all characters")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "failed request",
                    content = @Content),
    })
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CharacterDTO> getAllCharacters() {
        List<Character> characterList = characterService.findAll().stream().toList();
        return characterList.stream().map(characterMapper::characterToCharacterDTO).toList();
    }


    @Operation(summary = "Get Characters by movie ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "failed request    ",
                    content = @Content),
    })
    @GetMapping("/movie/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<CharacterDTO> getCharactersByMovieId(@PathVariable Integer id) {
        Set<Character> characterList = characterService.findByMovieId(id);
        return characterList.stream().map(characterMapper::characterToCharacterDTO).collect(Collectors.toSet());
    }

    @Operation(summary = "Get Characters by franchise ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "failed request    ",
                    content = @Content),
    })
    @GetMapping("/franchise/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<CharacterDTO> getCharactersByFranchiseId(@PathVariable Integer id) {
        Set<Character> characterList = characterService.findByFranchiseId(id);
        return characterList.stream().map(characterMapper::characterToCharacterDTO).collect(Collectors.toSet());
    }

    @Operation(summary = "Get a character by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "default",
                    description = "Character does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))}),

    })
    @GetMapping("{id}") // GET: localhost:8080/api/v1/characters/1
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity getById(@PathVariable Integer id) {
        CharacterDTO character = characterMapper.characterToCharacterDTO(characterService.findById(id));
        return ResponseEntity.ok(character);
    }

    @Operation(summary = "Get a character by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "failed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))})
    })
    @GetMapping("/name/{name}") // GET: localhost:8080/api/v1/characters/name
    @ResponseStatus(value = HttpStatus.OK)
    public Set<CharacterDTO> getByName(@PathVariable String name) {
        Set<Character> characterList = characterService.findByNameContainsIgnoreCase(name);
        return characterList.stream().map(characterMapper::characterToCharacterDTO).collect(Collectors.toSet());
    }


    @Operation(summary = "Adds new Character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "400",
                    description = "failed request",
                    content = @Content)
    })
    @PostMapping // POST: localhost:8080/api/v1/characters
    public ResponseEntity add(@RequestBody CharacterDTO characterDTO) {

        Character character = characterMapper.toCharacter(characterDTO);
        Character newCharacter = characterService.add(character);
        URI location = URI.create("characters/" + newCharacter.getId());
        return ResponseEntity.created(location).build();

    }


    @Operation(summary = "Delete a character by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "failed request",
                    content = @Content)
    })
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/characters/1
    public ResponseEntity deleteById(@PathVariable Integer id) {
        characterService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete a Character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "failed request",
                    content = @Content)
    })
    @DeleteMapping // DELETE: localhost:8080/api/v1/characters
    public ResponseEntity delete(@RequestBody CharacterDTO characterDTO) {
        characterService.delete(characterMapper.toCharacter(characterDTO));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update a character by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))}),
            @ApiResponse(responseCode = "400",
                    description = "failed request",
                    content = @Content)
    })
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/characters/1
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody CharacterDTO characterDTO) {

        if (id != characterDTO.getId()) {
            return ResponseEntity.badRequest().build();
        }

        characterService.update(characterMapper.toCharacter(characterDTO));

        return ResponseEntity.ok().build();
    }

}
