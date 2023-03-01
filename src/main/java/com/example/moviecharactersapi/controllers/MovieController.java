package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.dto.MovieGetDTO;
import com.example.moviecharactersapi.dto.MovieGetFromFranchiseDTO;
import com.example.moviecharactersapi.mappers.MovieMapper;
import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @Operation(summary = "Get all movies")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MovieGetDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Collection<MovieGetDTO>> findAllMovies() {
        return ResponseEntity.ok(movieService.findAll().stream().map(movie -> movieMapper.movieToMovieGetDTO(movie)).collect(Collectors.toList()));
    }

    @Operation(summary = "Update movie by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Movie successfully updated",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = MovieGetDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Movie not found with supplied ID",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @PutMapping("{id}")
    public ResponseEntity<MovieGetDTO> update(@RequestBody Movie movie, @PathVariable int id) {
        if(id != movie.getId()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(movieMapper.movieToMovieGetDTO(movieService.update(movie)));
    }


    @Operation(summary = "Get movie by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = MovieGetDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<MovieGetDTO> findMovieById(@PathVariable Integer id) {
        return ResponseEntity.ok(movieMapper.movieToMovieGetDTO(movieService.findById(id)));
    }

// Make a method that takes in a set of character IDs
// fetch the characters from tb_character
// use updateMovieCharactersByMovieId / similar method below to update the characters

    @Operation(summary = "Update movie's characters by movie ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Movie characters successfully updated",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = MovieGetDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Movie not found with supplied ID",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @PutMapping("{id}/characters")
    public ResponseEntity<MovieGetDTO> updateMovieCharactersById(
            @RequestBody Set<Character> characters,
            @PathVariable int id
    ) {
        Movie movie = movieService.findById(id);
        movie.setCharacters(characters);
        return ResponseEntity.ok(movieMapper.movieToMovieGetDTO(movieService.update(movie)));
    }

    @Operation(summary = "Update movie's franchise by movie ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Movie franchise successfully updated",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = MovieGetDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Movie not found with supplied ID",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @PutMapping("{id}/franchise")
    public ResponseEntity<MovieGetDTO> updateMovieFranchiseById(
            @RequestBody Franchise franchise,
            @PathVariable int id
    ){
        return ResponseEntity.ok(movieMapper.movieToMovieGetDTO(movieService.updateMovieFranchiseById(id, franchise)));
    }

    @Operation(summary = "Add a new movie")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Movie successfully created",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = MovieGetDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Movie not found with supplied ID",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @PostMapping
    public ResponseEntity<MovieGetDTO> add(@RequestBody Movie movie) {
        Movie tempMovie = movieService.add(movie);
        URI location = URI.create("/" + tempMovie.getId());
        return ResponseEntity.created(location).build();
    }

}
