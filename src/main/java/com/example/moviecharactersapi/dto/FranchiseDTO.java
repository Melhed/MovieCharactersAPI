package com.example.moviecharactersapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class FranchiseDTO {
    private int id;
    private String name;
    private String description;
    private List<Integer> movieIds;
}
