package com.example.moviecharactersapi.runners;


import com.example.moviecharactersapi.mappers.CharacterMapper;

import com.example.moviecharactersapi.repositories.CharacterRepositories;
import com.example.moviecharactersapi.services.CharacterService;
import com.example.moviecharactersapi.services.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {


    public AppRunner() {

    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

    }
}
