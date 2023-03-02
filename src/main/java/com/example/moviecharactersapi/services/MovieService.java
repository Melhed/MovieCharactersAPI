package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.entity.Franchise;
import com.example.moviecharactersapi.models.entity.Movie;

import java.util.List;
import java.util.Set;


public interface MovieService extends CRUDService<Movie, Integer> {

    Set<Movie> findMoviesInFranchise(Integer franchiseId);
    Movie updateCharactersInMovie(Integer movieId, List<Integer> characterIds);
    Movie updateFranchiseInMovie(Integer movieId, Franchise franchise);
    boolean movieExistsById(Integer movieId);

}
