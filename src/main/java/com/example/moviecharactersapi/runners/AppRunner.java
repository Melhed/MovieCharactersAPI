package com.example.moviecharactersapi.runners;

import com.example.moviecharactersapi.mappers.CharacterMapper;
import com.example.moviecharactersapi.repositories.CharacterRepositories;
import com.example.moviecharactersapi.services.CharacterService;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    private final CharacterService characterService;

    private final CharacterMapper characterMapper;

    public AppRunner(CharacterRepositories characterRepositories, CharacterService characterService, CharacterMapper characterMapper) {

        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

    }
}
