package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.models.dto.MovieGetDTO;
import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Franchise;
import com.example.moviecharactersapi.models.entity.Movie;
import com.example.moviecharactersapi.services.CharacterService;
import com.example.moviecharactersapi.services.FranchiseService;
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

    @Autowired
    private FranchiseService franchiseService;

    @Mapping(target = "franchiseId", source = "franchise", qualifiedByName = "franchiseToFranchiseId")
    @Mapping(target = "characterIds", source = "characters", qualifiedByName = "charactersToCharacterIds")
    public abstract MovieGetDTO movieToMovieGetDTO(Movie movie);

    @Mapping(target = "franchise", source = "franchiseId", qualifiedByName = "franchiseIdToFranchise")
    @Mapping(target = "characters", source = "characterIds", qualifiedByName = "characterIdsToCharacters")
    public abstract Movie toMovie(MovieGetDTO movieGetDTO);

    @Named("franchiseToFranchiseId")
    public Integer mapFranchiseToId(Franchise franchise) {
        if(franchise == null) return null;
        return franchise.getId();
    }

    @Named("franchiseIdToFranchise")
    public Franchise mapFranchiseIdToFranchise(Integer franchiseId) {
        if(franchiseId == null) return null;
        return franchiseService.findById(franchiseId);
    }

    @Named("characterIdsToCharacters")
    public Set<Character> mapCharacterIdsToCharacters(Set<Integer> movieIds) {
        if(movieIds == null) return null;
        return movieIds.stream().map(movieId -> characterService.findById(movieId)).collect(Collectors.toSet());
    }

    @Named("charactersToCharacterIds")
    public Set<Integer> mapCharactersToCharacterIds(Set<Character> source) {
        if(source == null) return null;
        return source.stream().map(Character::getId).collect(Collectors.toSet());
    }


}
