package com.example.moviecharactersapi.repositories;

import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CharacterRepositories extends JpaRepository<Character, Integer> {


    Optional<Set<Character>> findByNameContainsIgnoreCase(String name);



    Optional<Set<Character>> findByMoviesId(Integer id);





    Optional<Set<Character>> findByMoviesFranchiseId(Integer id);






}
