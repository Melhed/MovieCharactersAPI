package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.dto.MovieGetDTO;
import com.example.moviecharactersapi.dto.MovieGetFromFranchiseDTO;
import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.models.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "director", source = "director")
    @Mapping(target = "releaseYear", source = "release_year")
    @Mapping(target = "pictureUrl", source = "picture_url")
    @Mapping(target = "trailerUrl", source = "trailer_url")
    @Mapping(target = "franchiseId", source = "franchise.id")
    @Mapping(target = "characterIds", source = "characters", qualifiedByName = "charactersToCharacterIds")
    MovieGetDTO movieToMovieGetDTO(Movie movie);

//    @Mapping(target = "characters", source = "characterIds", qualifiedByName = "characterIdsToCharacters")
    Movie toMovie(MovieGetDTO movieGetDTO);

    @Mapping(target = "director", source = "director")
    @Mapping(target = "releaseYear", source = "release_year")
    @Mapping(target = "pictureUrl", source = "picture_url")
    @Mapping(target = "trailerUrl", source = "trailer_url")
    @Mapping(target = "characterIds", source = "characters", qualifiedByName = "charactersToCharacterIds")
    MovieGetFromFranchiseDTO movieToMovieGetFromFranchiseDTO(Movie movie);

    // Implement below methods once CharacterService has been added.
//    @Named("characterIdsToCharacters")
//    default Set<Character> mapCharacterIdsToCharacters(Integer id) {
//        return null;
//    }
//    default Character mapCharacterIdToCharacter(Integer id) {
//        return null;
//    }

    @Named("charactersToCharacterIds")
    default List<Object> map(Set<Character> source) {
        if(source == null) return null;
        return source.stream().map(character -> new idRecord(character.getId())).collect(Collectors.toList());
    }

    record idRecord(Integer id){};

}
