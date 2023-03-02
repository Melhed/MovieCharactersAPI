package com.example.moviecharactersapi.models.dto;

import com.example.moviecharactersapi.mappers.MovieMapper;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MovieGetDTO {

    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String pictureUrl;
    private String trailerUrl;
    private Set<Integer> characterIds;
    private int franchiseId;

}
