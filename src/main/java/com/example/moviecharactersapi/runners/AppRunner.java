package com.example.moviecharactersapi.runners;

import com.example.moviecharactersapi.repositories.CharacterRepositories;
import com.example.moviecharactersapi.services.CharacterService;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {


    private final CharacterService characterService;


    public AppRunner(CharacterRepositories characterRepositories, CharacterService characterService) {

        this.characterService = characterService;
    }


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

//        System.out.println(characterRepositories.findByNameContainsIgnoreCase("s"));


     /*   characterService.add(new Character("Melhed"));

        System.out.println(characterService.findByNameContainsIgnoreCase("sa"));
    }*/

    }
}
