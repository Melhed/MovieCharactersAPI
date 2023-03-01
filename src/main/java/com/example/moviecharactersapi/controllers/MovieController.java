package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.mappers.CharacterMapper;
import com.example.moviecharactersapi.models.dto.CharacterDTO;
import com.example.moviecharactersapi.models.dto.MovieGetDTO;
import com.example.moviecharactersapi.mappers.MovieMapper;
import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Franchise;
import com.example.moviecharactersapi.models.entity.Movie;
import com.example.moviecharactersapi.services.CharacterService;
import com.example.moviecharactersapi.services.FranchiseService;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;
    private final FranchiseService franchiseService;

    public MovieController(MovieService movieService, MovieMapper movieMapper, CharacterService characterService, CharacterMapper characterMapper, FranchiseService franchiseService) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterService = characterService;
        this.characterMapper = characterMapper;
        this.franchiseService = franchiseService;
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
    public ResponseEntity<MovieGetDTO> update(
            @RequestBody Movie movie,
            @PathVariable int id
    ) {
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
            @RequestBody List<Integer> characterIds,
            @PathVariable int id
    ) {
        if(characterIds == null) return null;
        return ResponseEntity.ok(movieMapper.movieToMovieGetDTO(movieService.updateCharactersInMovie(id, characterIds)));
    }

    @GetMapping("{id}/characters")
    public ResponseEntity<Collection<CharacterDTO>> getCharactersByMovieId(@PathVariable int id) {
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok(movie.getCharacters().stream().map(character -> characterMapper.dtoToCharacter(character)).collect(Collectors.toList()));
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
        return ResponseEntity.ok(movieMapper.movieToMovieGetDTO(movieService.updateFranchiseInMovie(id, franchise)));
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

    @Operation(summary = "Delete movie")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Movie successfully deleted",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Movie not found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @DeleteMapping
    public ResponseEntity delete(@RequestBody Movie movie) {
        movieService.delete(movie);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete movie by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Movie successfully deleted",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Movie with ID not found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
