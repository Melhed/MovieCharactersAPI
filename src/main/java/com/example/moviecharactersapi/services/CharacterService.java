package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Movie;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface CharacterService extends CRUDService<Character, Integer> {




     Set<Character> findByNameContainsIgnoreCase(String name);


     Set<Character> findByMovieId(Integer id);

     Set<Character> findByFranchiseId(Integer id);





}
