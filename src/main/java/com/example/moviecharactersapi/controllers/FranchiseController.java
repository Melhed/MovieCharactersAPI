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

    @GetMapping
    public ResponseEntity<Collection<FranchiseDTO>> findAllFranchises() {
        return ResponseEntity.ok(
                franchiseService.findAll().stream()
                .map(franchise -> franchiseMapper.franchiseToFranchiseDTO(franchise))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<FranchiseDTO> findFranchiseById(@PathVariable int id) {
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.findById(id)));
    }

    @GetMapping("{id}/characters")
    public ResponseEntity<Set<CharacterDTO>> findCharactersByFranchiseId(@PathVariable int id) {
        Set<Movie> movies = franchiseService.findById(id).getMovies();
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

    @GetMapping("{id}/movies")
    public ResponseEntity<List<MovieGetDTO>> findFranchiseMoviesById(@PathVariable int id) {
        Franchise franchise = franchiseService.findById(id);

        return ResponseEntity.ok(
                franchise.getMovies().stream()
                .map(movie -> movieMapper.movieToMovieGetDTO(movie))
                .collect(Collectors.toList())
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<FranchiseDTO> update(@RequestBody Franchise franchise, @PathVariable int id) {
        if(id != franchise.getId()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.update(franchise)));
    }

    @PutMapping("{id}/movies")
    public ResponseEntity<FranchiseDTO> updateFranchiseMoviesById(
            @RequestBody List<Integer> movieIds,
            @PathVariable int id
    ) {
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.updateMoviesInFranchise(id, movieIds)));
    }

    @PostMapping
    public ResponseEntity<FranchiseDTO> add(@RequestBody Franchise franchise) {
        Franchise tempFranchise = franchiseService.add(franchise);
        URI location = URI.create("/" + tempFranchise.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Franchise franchise) {
        franchiseService.delete(franchise);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
