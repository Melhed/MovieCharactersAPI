package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.models.dto.CharacterDTO;
import com.example.moviecharactersapi.models.entity.Character;
import com.example.moviecharactersapi.models.entity.Movie;
import com.example.moviecharactersapi.services.MovieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class CharacterMapper {
    @Autowired
    MovieService movieService;

    @Mapping(target = "movies", source = "movieIDs", qualifiedByName = "idsToMovies")
    public abstract Character toCharacter(CharacterDTO characterDTO);

    @Mapping(target = "movieIDs", source = "movies", qualifiedByName = "moviesToIDs")
    public abstract CharacterDTO characterToCharacterDTO(Character character);

    @Named("moviesToIDs")
    Set<Integer> mapMoviesToIDs(Set<Movie> source) {
        if (source == null) {
            return null;
        }
        return source.stream().map(movie -> movie.getId()).collect(Collectors.toSet());

    }

    @Named("idsToMovies")
    Set<Movie> mapIDsToMovies(Set<Integer> source) {
        if (source == null) {
            return null;
        }
        return source.stream().map(movieId ->
                movieService.findById(movieId)
        ).collect(Collectors.toSet());
    }


}
