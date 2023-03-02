package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.mappers.CharacterMapper;
import com.example.moviecharactersapi.mappers.FranchiseMapper;
import com.example.moviecharactersapi.models.dto.CharacterDTO;
import com.example.moviecharactersapi.models.dto.FranchiseDTO;
import com.example.moviecharactersapi.models.dto.MovieGetDTO;
import com.example.moviecharactersapi.mappers.MovieMapper;
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
    private final FranchiseMapper franchiseMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper, CharacterService characterService, CharacterMapper characterMapper, FranchiseService franchiseService, FranchiseMapper franchiseMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterService = characterService;
        this.characterMapper = characterMapper;
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
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
    @PutMapping("{movieId}")
    public ResponseEntity<MovieGetDTO> update(
            @RequestBody MovieGetDTO movieDTO,
            @PathVariable int movieId
    ) {
        if(movieId != movieDTO.getId()) return ResponseEntity.badRequest().build();

        Movie movie = movieMapper.toMovie(movieDTO);
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
    @GetMapping("{movieId}")
    public ResponseEntity<MovieGetDTO> findMovieById(@PathVariable Integer movieId) {
        return ResponseEntity.ok(movieMapper.movieToMovieGetDTO(movieService.findById(movieId)));
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
    @PutMapping("{movieId}/characters")
    public ResponseEntity<MovieGetDTO> updateMovieCharactersById(
            @RequestBody List<Integer> characterIds,
            @PathVariable int movieId
    ) {
        if(characterIds == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(movieMapper.movieToMovieGetDTO(movieService.updateCharactersInMovie(movieId, characterIds)));
    }

    @Operation(summary = "Get movie's characters by movie ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = CharacterDTO.class))}
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
    @GetMapping("{movieId}/characters")
    public ResponseEntity<Collection<CharacterDTO>> getCharactersByMovieId(@PathVariable int movieId) {
        Movie movie = movieService.findById(movieId);
        return ResponseEntity.ok(movie.getCharacters().stream().map(character -> characterMapper.characterToCharacterDTO(character)).collect(Collectors.toList()));
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
    @PutMapping("{movieId}/franchise")
    public ResponseEntity<MovieGetDTO> updateMovieFranchiseById(
            @RequestBody FranchiseDTO franchiseDTO,
            @PathVariable int movieId
    ){
        Franchise franchise = franchiseMapper.franchiseDTOToFranchise(franchiseDTO);
        return ResponseEntity.ok(movieMapper.movieToMovieGetDTO(movieService.updateFranchiseInMovie(movieId, franchise)));
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
    public ResponseEntity<MovieGetDTO> add(@RequestBody MovieGetDTO movieDTO) {
        Movie movie = movieService.add(movieMapper.toMovie(movieDTO));
        URI location = URI.create("/" + movie.getId());
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
                            content = @Content),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @DeleteMapping
    public ResponseEntity delete(@RequestBody MovieGetDTO movieDTO) {
        movieService.delete(movieMapper.toMovie(movieDTO));
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
                            content = @Content),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("{movieId}")
    public ResponseEntity deleteById(@PathVariable int movieId) {
        movieService.deleteById(movieId);
        return ResponseEntity.noContent().build();
    }

}
