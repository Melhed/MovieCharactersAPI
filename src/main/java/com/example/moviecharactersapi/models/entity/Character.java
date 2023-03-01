package com.example.moviecharactersapi.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

}
