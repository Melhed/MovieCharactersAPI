package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.repositories.CharacterRepositories;

import java.util.Collection;
import java.util.Set;


public interface CharacterService extends CrudService<Character, Integer> {



    @Override
    public Character save(Character object);






   public Set<Character> findByNameContainsIgnoreCase(String name);

    @Override
    public Character findById(Integer id);

    @Override
    public Character add(Character entity);

    @Override
    public Collection<Character> findAll();

    @Override
    public Character update(Character entity);
    @Override
    public void delete(Character entity);

    @Override
    public void deleteById(Integer id);

}
