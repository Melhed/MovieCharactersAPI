package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.models.dto.MovieGetDTO;
import com.example.moviecharactersapi.models.dto.MovieGetFromFranchiseDTO;
import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Movie;
import com.example.moviecharactersapi.services.CharacterService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    @Autowired
    private CharacterService characterService;

    @Mapping(target = "director", source = "director")
    @Mapping(target = "releaseYear", source = "release_year")
    @Mapping(target = "pictureUrl", source = "picture_url")
    @Mapping(target = "trailerUrl", source = "trailer_url")
    @Mapping(target = "franchiseId", source = "franchise.id")
    @Mapping(target = "characterIds", source = "characters", qualifiedByName = "charactersToCharacterIds")
    public abstract MovieGetDTO movieToMovieGetDTO(Movie movie);

//    @Mapping(target = "characters", source = "characterIds", qualifiedByName = "characterIdsToCharacters")
    public abstract Movie toMovie(MovieGetDTO movieGetDTO);

    @Mapping(target = "director", source = "director")
    @Mapping(target = "releaseYear", source = "release_year")
    @Mapping(target = "pictureUrl", source = "picture_url")
    @Mapping(target = "trailerUrl", source = "trailer_url")
    @Mapping(target = "characterIds", source = "characters", qualifiedByName = "charactersToCharacterIds")
    public abstract MovieGetFromFranchiseDTO movieToMovieGetFromFranchiseDTO(Movie movie);

    @Named("characterIdsToCharacters")
    public List<Character> map(List<idRecord> movieIds) {
        if(movieIds == null) return null;
        return movieIds.stream().map(entry -> characterService.findById(entry.id())).collect(Collectors.toList());
    }

    @Named("charactersToCharacterIds")
    public List<idRecord> map(Set<Character> source) {
        if(source == null) return null;
        return source.stream().map(character -> new idRecord(character.getId())).collect(Collectors.toList());
    }

    public record idRecord(Integer id){};

}
