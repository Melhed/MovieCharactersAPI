package com.example.moviecharactersapi.runners;

import com.example.moviecharactersapi.repositories.CharacterRepositories;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    private final CharacterRepositories characterRepositories;

    public AppRunner(CharacterRepositories characterRepositories) {
        this.characterRepositories = characterRepositories;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("Hello World!");
    }


}
