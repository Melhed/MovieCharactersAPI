package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.exceptions.CharacterNotFoundException;
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

    private final MovieService movieService;


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
        return characterRepositories.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
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
            Character character = characterRepositories.findById(id).get();

            character.getMovies().forEach(movie -> {

                Set<Character> characters = movie.getCharacters();

                characters.remove(character);

                movie.setCharacters(characters);

                movieService.update(movie);

            });

            characterRepositories.delete(character);

        } else {
            System.out.println("no professor exist with that id");
        }

    }

    @Override
    public void delete(Character entity) {

    }
}
