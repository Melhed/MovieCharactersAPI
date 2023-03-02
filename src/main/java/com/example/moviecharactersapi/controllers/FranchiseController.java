package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.mappers.CharacterMapper;
import com.example.moviecharactersapi.models.dto.CharacterDTO;
import com.example.moviecharactersapi.models.dto.FranchiseDTO;
import com.example.moviecharactersapi.models.dto.MovieGetDTO;
import com.example.moviecharactersapi.mappers.FranchiseMapper;
import com.example.moviecharactersapi.mappers.MovieMapper;
import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Franchise;
import com.example.moviecharactersapi.models.entity.Movie;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/franchise")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;
    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final CharacterMapper characterMapper;

    public FranchiseController(FranchiseService franchiseService, FranchiseMapper franchiseMapper, MovieService movieService, MovieMapper movieMapper, CharacterMapper characterMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }

    @Operation(summary = "Get all franchises")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = FranchiseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Collection<FranchiseDTO>> findAllFranchises() {
        return ResponseEntity.ok(
                franchiseService.findAll().stream()
                .map(franchise -> franchiseMapper.franchiseToFranchiseDTO(franchise))
                .collect(Collectors.toList())
        );
    }

    @Operation(summary = "Get franchise by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = FranchiseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Franchise not found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @GetMapping("{franchiseId}")
    public ResponseEntity<FranchiseDTO> findFranchiseById(@PathVariable int franchiseId) {
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.findById(franchiseId)));
    }

    @Operation(summary = "Get characters in franchise")
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
                            description = "Franchise not found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @GetMapping("{franchiseId}/characters")
    public ResponseEntity<Set<CharacterDTO>> findCharactersByFranchiseId(@PathVariable int franchiseId) {
        Set<Movie> movies = franchiseService.findById(franchiseId).getMovies();
        Set<Character> characters = new HashSet<>();

        for (Movie movie : movies) {
            characters.addAll(movie.getCharacters());
        }

        return ResponseEntity.ok(
                characters.stream()
                .map(character -> characterMapper.dtoToCharacter(character))
                .collect(Collectors.toSet())
        );
    }

    @Operation(summary = "Get movies in franchise")
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
                            responseCode = "404",
                            description = "Franchise not found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @GetMapping("{franchiseId}/movies")
    public ResponseEntity<List<MovieGetDTO>> findFranchiseMoviesById(@PathVariable int franchiseId) {
        Franchise franchise = franchiseService.findById(franchiseId);

        return ResponseEntity.ok(
                franchise.getMovies().stream()
                .map(movie -> movieMapper.movieToMovieGetDTO(movie))
                .collect(Collectors.toList())
        );
    }

    @Operation(summary = "Update franchise by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = FranchiseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Franchise not found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @PutMapping("{franchiseId}")
    public ResponseEntity<FranchiseDTO> update(
            @RequestBody FranchiseDTO franchiseDTO,
            @PathVariable int franchiseId
    ) {
        if(franchiseId != franchiseDTO.getId()) return ResponseEntity.badRequest().build();
        Franchise franchise = franchiseMapper.franchiseDTOToFranchise(franchiseDTO);
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.update(franchise)));
    }

    @Operation(summary = "Update movies in franchise")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = FranchiseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Franchise not found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @PutMapping("{franchiseId}/movies")
    public ResponseEntity<FranchiseDTO> updateFranchiseMoviesById(
            @RequestBody List<Integer> movieIds,
            @PathVariable int franchiseId
    ) {
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.updateMoviesInFranchise(franchiseId, movieIds)));
    }

    @Operation(summary = "Create franchise")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Success",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = FranchiseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Franchise not found",
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
    public ResponseEntity<FranchiseDTO> add(@RequestBody FranchiseDTO franchiseDTO) {
        Franchise franchise = franchiseMapper.franchiseDTOToFranchise(franchiseDTO);
        Franchise tempFranchise = franchiseService.add(franchise);
        URI location = URI.create("/" + tempFranchise.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Delete franchise")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Success",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Franchise not found",
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
    public ResponseEntity delete(@RequestBody FranchiseDTO franchiseDTO) {
        franchiseService.delete(franchiseMapper.franchiseDTOToFranchise(franchiseDTO));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete franchise by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Success",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Franchise not found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "default",
                            description = "Failed request",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("{franchiseId}")
    public ResponseEntity deleteById(@PathVariable int franchiseId) {
        franchiseService.deleteById(franchiseId);
        return ResponseEntity.noContent().build();
    }
}
