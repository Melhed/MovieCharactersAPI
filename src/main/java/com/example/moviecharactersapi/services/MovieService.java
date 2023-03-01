package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.entity.Franchise;
import com.example.moviecharactersapi.models.entity.Movie;

import java.util.Set;


public interface MovieService extends CRUDService<Movie, Integer> {

    Set<Movie> findMoviesByFranchiseId(Integer id);
    Movie updateMovieFranchiseById(Integer id, Franchise franchise);

}
