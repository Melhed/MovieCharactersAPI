package com.example.moviecharactersapi.repositories;

import com.example.moviecharactersapi.models.Character;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CharacterRepositories extends JpaRepository<Character, Integer> {

    Optional<Set<Character>> findByNameContainsIgnoreCase(String name);


    @Transactional
    Optional<Character> findByid(Integer id);


    // get all the characters

    List<Character> findAll();




}
