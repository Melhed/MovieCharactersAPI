package com.example.moviecharactersapi.models.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
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

    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies;

}
