package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.models.dto.FranchiseDTO;
import com.example.moviecharactersapi.models.entity.Franchise;
import com.example.moviecharactersapi.models.entity.Movie;
import com.example.moviecharactersapi.services.MovieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FranchiseMapper {
    @Autowired
    MovieService movieService;

    @Mapping(target = "movies", source = "movieIds", qualifiedByName = "movieIdsToMovies")
    public abstract Franchise franchiseDTOToFranchise(FranchiseDTO franchiseDTO);

    @Mapping(target = "movieIds", source = "movies", qualifiedByName = "moviesToMovieIds")
    public abstract FranchiseDTO franchiseToFranchiseDTO(Franchise franchise);

    @Named("movieIdsToMovies")
    Set<Movie> mapMovies(Set<Integer> movieIds) {
        if(movieIds == null) return null;
        return movieIds.stream().map(movieId -> movieService.findById(movieId)).collect(Collectors.toSet());
    }

    @Named("moviesToMovieIds")
    Set<Integer> mapIds(Set<Movie> movies) {
        if(movies == null) return null;
        return movies.stream().map(movie -> movie.getId()).collect(Collectors.toSet());
    }

}
