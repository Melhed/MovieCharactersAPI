package com.example.moviecharactersapi.models.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class FranchiseDTO {
    private int id;
    private String name;
    private String description;
    private Set<Integer> movieIds;
}
