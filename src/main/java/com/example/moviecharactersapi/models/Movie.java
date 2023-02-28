package com.example.moviecharactersapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tb_movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @Column(name = "movie_title", nullable = false, length = 100)
    private String title;

    @Column(name = "movie_genre")
    private String genre;

    @Column(name = "movie_release_year", nullable = false)
    private int release_year;

    @Column(name = "movie_director", length = 100)
    private String director;

    @Column(name = "movie_picture_url")
    private String picture_url;

    @Column(name = "movie_trailer_url")
    private String trailer_url;

    @JsonGetter("characters")
    public Set<Integer> jsonGetCharacters() {
        if(characters != null) {
            Set<Integer> set = new HashSet<>();
            characters.forEach(character -> set.add(character.getId()));
            return set;
        }
        return null;
    }

    @ManyToMany
    private Set<Character> characters;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    public Movie(){}
    public Movie(int id, String title, String genre, int release_year, String director, String picture_url, String trailer_url){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.release_year = release_year;
        this.director = director;
        this.picture_url = picture_url;
        this.trailer_url = trailer_url;
    }


}
