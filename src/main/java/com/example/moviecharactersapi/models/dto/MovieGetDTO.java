package com.example.moviecharactersapi.models.dto;

import com.example.moviecharactersapi.mappers.MovieMapper;
import lombok.Data;

import java.util.List;

@Data
public class MovieGetDTO {

    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String pictureUrl;
    private String trailerUrl;
    private List<MovieMapper.idRecord> characterIds;
    private int franchiseId;

}
