package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.entity.Franchise;

import java.util.List;

public interface FranchiseService extends CRUDService<Franchise, Integer>{

    Franchise updateMoviesInFranchise(int franchiseId, List<Integer> movieIds);

}
