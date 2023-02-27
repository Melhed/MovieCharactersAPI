package com.example.moviecharactersapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepositories extends JpaRepository<Character, Integer> {


}
