package com.example.moviecharactersapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieGetFromFranchiseDTO {

    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String pictureUrl;
    private String trailerUrl;
    private List<Object> characterIds;

}
