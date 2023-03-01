package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.repositories.CharacterRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepositories characterRepositories;

/*
    public CharacterServiceImpl(CharacterRepositories characterRepositories) {
        this.characterRepositories = characterRepositories;
    }
*/

    @Override
    public Set<Character> findByNameContainsIgnoreCase(String name) {
        return characterRepositories.findByNameContainsIgnoreCase(name).get();
    }

    @Override
    public Set<Character> findByMovieId(Integer id) {

        return characterRepositories.findByMoviesId(id).get();

    }

    @Override
    public Set<Character> findByFranchiseId(Integer id) {

       return characterRepositories.findByMoviesFranchiseId(id).get();
    }

    @Override
    public Character findById(Integer id) {
        return characterRepositories.findById(id).get();
    }

    @Override
    public Character add(Character entity) {

        return characterRepositories.save(entity);

    }

    @Override
    public List<Character> findAll() {

        return characterRepositories.findAll();
    }

    @Override
    public Character update(Character entity) {






        return characterRepositories.save(entity);
    }



    @Override
    public void deleteById(Integer id) {

        if (characterRepositories.existsById(id)) {
            Character character = characterRepositories.findByid(id).get();

            character.getMovies().forEach(s -> s.setCharacters(null));

            characterRepositories.delete(character);

            //first check if the character has a movie
            //if it does, delete the movie first
            //then delete the character

            //note to self: Check noroff repo
        }else {
            System.out.println("no professor exist with that id");
        }

    }
}
