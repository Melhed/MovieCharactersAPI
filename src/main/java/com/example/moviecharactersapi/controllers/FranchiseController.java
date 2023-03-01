package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.dto.FranchiseDTO;
import com.example.moviecharactersapi.dto.MovieGetDTO;
import com.example.moviecharactersapi.mappers.FranchiseMapper;
import com.example.moviecharactersapi.mappers.MovieMapper;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.services.FranchiseService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/franchise")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;
    private final MovieMapper movieMapper;

    public FranchiseController(FranchiseService franchiseService, FranchiseMapper franchiseMapper, MovieMapper movieMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public ResponseEntity<Collection<FranchiseDTO>> findAllFranchises() {
        return ResponseEntity.ok(franchiseService.findAll().stream().map(franchise -> franchiseMapper.franchiseToFranchiseDTO(franchise)).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<FranchiseDTO> findFranchiseById(@PathVariable int id) {
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.findById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<FranchiseDTO> update(@RequestBody Franchise franchise, @PathVariable int id) {
        if(id != franchise.getId()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDTO(franchiseService.update(franchise)));
    }

    @GetMapping("{id}/movies")
    public ResponseEntity<List<MovieGetDTO>> findFranchiseMoviesById(@PathVariable int id) {
        Franchise franchise = franchiseService.findById(id);
        return ResponseEntity.ok(franchise.getMovies().stream().map(movie -> movieMapper.movieToMovieGetDTO(movie)).collect(Collectors.toList()));
    }

    @PutMapping("{id}/movies")
    public ResponseEntity<List<MovieGetDTO>> updateFranchiseMoviesById(@RequestBody List<Integer> movieIds, @PathVariable int id) {
        if(movieIds == null) return null;
        Franchise franchise = franchiseService.updateFranchiseMoviesById(id, movieIds);
        return ResponseEntity.ok(franchise.getMovies().stream().map(movie -> movieMapper.movieToMovieGetDTO(movie)).collect(Collectors.toList()));
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
