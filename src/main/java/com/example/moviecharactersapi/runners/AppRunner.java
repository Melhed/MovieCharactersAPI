package com.example.moviecharactersapi.runners;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.MovieRepository;
import com.example.moviecharactersapi.services.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    private final MovieRepository movieRepository;
    private final MovieService movieService;

    public AppRunner(MovieRepository movieRepository, MovieService movieService) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Movie movie = new Movie(99, "Mushishi", "Anime", 2000, "Melv", "ahsahah", "ahsahsah");
        Movie tempMovie = movieService.add(movie);
        Franchise franchise = new Franchise(1, "The Lord of the Rings", "A trilogy made by JR tolkien");
        movieService.updateMovieFranchiseById(13, franchise);
    }


}
