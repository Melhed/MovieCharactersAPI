package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;

import java.util.Set;


public interface MovieService extends CRUDService<Movie, Integer> {

    Set<Movie> findMoviesByFranchiseId(Integer id);
    Movie updateMovieFranchiseById(Integer id, Franchise franchise);

}
