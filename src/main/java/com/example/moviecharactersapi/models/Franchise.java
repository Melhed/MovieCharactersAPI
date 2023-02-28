package com.example.moviecharactersapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "tb_franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_id")
    private int id;

    @Column(name = "franchise_name", nullable = false, length = 100)
    private String name;

    @Column(name = "franchise_description")
    private String description;

    @JsonGetter("movies")
    public Set<Integer> jsonGetMovies() {
        if(movies == null) return null;
        return movies.stream().map(movie -> movie.getId()).collect(Collectors.toSet());
    }

    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies;

    public Franchise() {}

    public Franchise(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
