package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.mappers.MovieMapper;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public Movie findById(Integer movieId) {
        return movieRepository.findById(movieId).get();
    }

    @Override
    public Set<Movie> findMoviesByFranchiseId(Integer id) {
        return movieRepository.findMoviesByFranchiseId(id);
    }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie add(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovieFranchiseById(Integer id, Franchise franchise) {
        if(franchise == null) return null;
        Movie movie = findById(id);
        movie.setFranchise(franchise);
        return update(movie);
    }

    @Override
    public Movie update(Movie movie) {
        if(movie == null) return null;
        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(Integer movieId) {
        if(movieId == null) return;
        movieRepository.deleteById(movieId);
    }

    @Override
    public void delete(Movie movie) {
        if(movie == null) return;
        movieRepository.delete(movie);
    }

}
