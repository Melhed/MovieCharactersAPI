package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.models.dto.CharacterDTO;
import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface CharacterMapper {

    Character toCharacter(CharacterDTO characterDTO);


    @Mapping(target = "movieIDs", source = "movies", qualifiedByName = "moviesToIDs")
    CharacterDTO dtoToCharacter(Character character);

    @Named("moviesToIDs")
    default Set<Integer> map(Set<Movie> source) {

        if (source != null) {
            return source.stream().map(Movie::getId).collect(Collectors.toSet());
        }
        return null;
    }


}
