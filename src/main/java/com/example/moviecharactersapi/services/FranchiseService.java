package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.Franchise;

import java.util.List;

public interface FranchiseService extends CRUDService<Franchise, Integer>{

    Franchise updateFranchiseMoviesById(int id, List<Integer> movieIds);

}
