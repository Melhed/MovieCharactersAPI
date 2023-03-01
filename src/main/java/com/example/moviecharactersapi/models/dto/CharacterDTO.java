package com.example.moviecharactersapi.models.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CharacterDTO {

    private int id;
    private String name;
    private String alias;
    private String gender;
    private String picture_url;
    private Set<Integer> movieIDs;
}
