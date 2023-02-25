package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.controllers.CharacterDTO;
import com.example.moviecharactersapi.models.Character;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CharacterDTOMapper implements Function<Character, CharacterDTO> {
    @Override
    public CharacterDTO apply(Character character) {
        return new CharacterDTO(
                character.getId(),
                character.getName(),
                character.getAlias(),
                character.getGender(),
                character.getPicture_url()
        );
    }
}
