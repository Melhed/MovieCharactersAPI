package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Movie;
import com.example.moviecharactersapi.repositories.CharacterRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepositories characterRepositories;

    public CharacterServiceImpl(CharacterRepositories characterRepositories) {
        this.characterRepositories = characterRepositories;
    }


    /**
     * This method is used to find characters by name, it checks if the name is contained in the name of the character
     *
     * @param name
     * @return Set of Characters
     */

    @Override
    public Set<Character> findByNameContainsIgnoreCase(String name) {
        return characterRepositories.findByNameContainsIgnoreCase(name).get();
    }

    /**
     * This method is used to find characters by movie id
     *
     * @param id
     * @return Set of Characters
     */
    @Override
    public Set<Character> findByMovieId(Integer id) {
        return characterRepositories.findByMoviesId(id).get();
    }

    /**
     * This method is used to find characters by franchise id
     *
     * @param id
     * @return Set of Characters
     */
    @Override
    public Set<Character> findByFranchiseId(Integer id) {
        return characterRepositories.findByMoviesFranchiseId(id).get();
    }

    /**
     * This method is used to find a character by id
     *
     * @param id
     * @return Character
     */
    @Override
    public Character findById(Integer id) {
        return characterRepositories.findById(id).get();

    }

    /**
     * This method is used to add a character
     *
     * @param entity
     * @return
     */
    @Override
    public Character add(Character entity) {
        return characterRepositories.save(entity);
    }

    /**
     * This method is used to find all characters
     *
     * @return List of Characters
     */
    @Override
    public List<Character> findAll() {
        return characterRepositories.findAll();
    }

    /**
     * This method is used to update a character
     *
     * @param entity
     * @return Character
     */
    @Override
    public Character update(Character entity) {
        return characterRepositories.save(entity);
    }

    /**
     * This method is used to delete a character by id
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {

        if (characterRepositories.existsById(id)) {
            Character character = characterRepositories.findById(id).get();
            Set<Movie> characterMovies = character.getMovies();

            for(Movie movie : characterMovies) {
                movie.getCharacters().remove(character);
            }

            characterRepositories.delete(character);

        } else {
            System.out.println("no professor exist with that id");
        }

    }

    /**
     * This method is used to delete a character
     *
     * @param entity
     */
    @Override
    public void delete(Character entity) {

        if (entity == null)
            throw new IllegalArgumentException("The entity must not be null!");

        deleteById(entity.getId());

    }
}
