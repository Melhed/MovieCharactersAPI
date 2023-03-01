/*
package com.example.moviecharactersapi.models.dto;

import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CharacterMapperImpl implements CharacterMapper{


    @Override
    public Character toCharacter(CharacterDTO characterDTO) {

        Character character = new Character();

        character.setId(characterDTO.getId());
        character.setName(characterDTO.getName());
        character.setAlias(characterDTO.getAlias());
        character.setGender(characterDTO.getGender());
        character.setPicture_url(characterDTO.getPicture_url());

        return character;

    }

    @Override
    public CharacterDTO dtoToCharacter(Character character) {

        CharacterDTO characterDTO = new CharacterDTO();

        characterDTO.setId(character.getId());
        characterDTO.setName(character.getName());
        characterDTO.setAlias(character.getAlias());
        characterDTO.setGender(character.getGender());
        characterDTO.setPicture_url(character.getPicture_url());

        if (character.getMovies() != null) {
//            character.getMovies().forEach(movie -> characterDTO.getMovies().add(movie.getId()));
            Set<Integer> moviesID = character.getMovies().stream().map(Movie::getId).collect(Collectors.toSet());
            characterDTO.setMovies(moviesID);
        }

        return characterDTO;

    }
}
*/
