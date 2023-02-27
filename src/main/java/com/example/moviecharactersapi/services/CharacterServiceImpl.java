package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.repositories.CharacterRepositories;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

  private final CharacterRepositories characterRepositories;

    public CharacterServiceImpl(CharacterRepositories characterRepositories) {
        this.characterRepositories = characterRepositories;
    }


    @Override
    public Character save(Character object) {
        return null;
    }


    @Override
    public Set<Character> findByNameContainsIgnoreCase(String name) {
        return characterRepositories.findByNameContainsIgnoreCase(name).get();
    }

    @Override
    public Character findById(Integer id) {
        return characterRepositories.findById(id).get();
    }

    @Override
    public Character add(Character entity) {

        characterRepositories.save(entity);
        return null;
    }

    @Override
    public Collection<Character> findAll() {
        return null;
    }

    @Override
    public Character update(Character entity) {
        return null;
    }

    @Override
    public void delete(Character entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
