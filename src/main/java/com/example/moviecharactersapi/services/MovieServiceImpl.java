package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.mappers.MovieMapper;
import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Franchise;
import com.example.moviecharactersapi.models.entity.Movie;
import com.example.moviecharactersapi.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final CharacterService characterService;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper, CharacterServiceImpl characterService) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.characterService = characterService;
    }

    /**
     * Gets movie based on ID
     * @param   movieId     ID of movie
     * @return              movie
     */
    @Override
    public Movie findById(Integer movieId) {
        return movieRepository.findById(movieId).get();
    }

    /**
     * Gets movies in franchise based on franchise ID
     * @param   franchiseId     ID of franchise
     * @return                  movies in franchise
     */
    @Override
    public Set<Movie> findMoviesInFranchise(Integer franchiseId) {
        return movieRepository.findMoviesByFranchiseId(franchiseId);
    }

    /**
     * Gets all movies
     * @return  collection of all movies
     */
    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * Adds movie
     * @param   movie   movie to be added
     * @return          added movie
     */
    @Override
    public Movie add(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Updates movie
     * @param   movie   movie to be updated
     * @return          updated movie
     */
    @Override
    public Movie update(Movie movie) {
        if(movie == null) return null;
        return movieRepository.save(movie);
    }

    /**
     * Updates movie's franchise
     * @param movieId
     * @param franchise
     * @return
     */
    @Override
    public Movie updateFranchiseInMovie(Integer movieId, Franchise franchise) {
        if(franchise == null) return null;
        Movie movie = findById(movieId);
        movie.setFranchise(franchise);
        return movieRepository.save(movie);
    }

    /**
     * Updates characters in movie
     * @param   movieId         ID of movie to be updated
     * @param   characterIds    IDs of characters
     * @return                  updated movie
     */
    @Override
    public Movie updateCharactersInMovie(int movieId, List<Integer> characterIds) {
        Movie movie = movieRepository.findById(movieId).get();
        Set<Character> charactersToAdd = characterIds.stream().map(characterId -> characterService.findById(characterId)).collect(Collectors.toSet());
        movie.setCharacters(charactersToAdd);

        for (Character character : charactersToAdd) {
            Set<Movie> characterMovies = character.getMovies();
            characterMovies.add(movie);
            character.setMovies(characterMovies);
        }

        return movieRepository.save(movie);
    }

    /**
     * Deletes movie
     * @param movie movie to delete
     */
    @Override
    public void delete(Movie movie) {
        if(movie == null) return;
        deleteById(movie.getId());
    }

    /**
     * Deletes movie with ID
     * @param movieId   ID of movie to delete
     */
    @Override
    public void deleteById(Integer movieId) {
        if(movieId == null) return;
        Movie movie = movieRepository.findById(movieId).get();

        Set<Character> characters = movie.getCharacters();
        for (Character character : characters) {
            Set<Movie> characterMovies = character.getMovies();
            characterMovies.remove(movie);
            character.setMovies(characterMovies);
            characterService.update(character);
        }

        movieRepository.deleteById(movieId);
    }

}
