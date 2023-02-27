package com.example.moviecharactersapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tb_character")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private int id;

    @Column(name = "character_name", length = 100, nullable = false)
    private String name;

    @Column(name = "character_alias", length = 50)
    private String alias;

    @Column(name = "character_gender", length = 50)
    private String gender;

    @Column(name = "character_picture_url")
    private String picture_url;

    //@JsonIgnore
    @ManyToMany(mappedBy = "characters")
    private Set<Movie> movies;


    public Character(String name) {
        this.name = name;
    }

    public Character() {

    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getGender() {
        return gender;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
