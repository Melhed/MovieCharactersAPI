package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.entity.Franchise;

import java.util.List;

public interface FranchiseService extends CRUDService<Franchise, Integer>{

    Franchise updateMoviesInFranchise(Integer franchiseId, List<Integer> movieIds);
    boolean franchiseExistsById(Integer franchiseId);

}
