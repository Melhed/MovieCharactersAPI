package com.example.moviecharactersapi.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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


    @ManyToMany
    private Set<Character> characters;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

}
