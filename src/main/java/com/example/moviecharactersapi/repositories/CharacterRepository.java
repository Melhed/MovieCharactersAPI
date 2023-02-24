package com.example.moviecharactersapi.repositories;

import com.example.moviecharactersapi.models.Character;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CharacterRepository implements CRUDRepository<Character, Integer> {
    private final String url;
    private final String username;
    private final String password;

    public CharacterRepository(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
    ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Character> findAll() {
        List<Character> characterList = new ArrayList<>();
        String sql = "SELECT character_id, character_alias, character_gender, character_name, character_picture_url FROM tb_character";

        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Character character = new Character();
                character.setId(result.getInt("character_id"));
                character.setAlias(result.getString("character_alias"));
                character.setGender(result.getString("character_gender"));
                character.setName(result.getString("character_name"));
                character.setPicture_url(result.getString("character_picture_url"));
                characterList.add(character);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return characterList;
    }

    @Override
    public Character findById(Integer id) {
        return null;
    }

    @Override
    public int insert(Character object) {
        return 0;
    }

    @Override
    public int update(Character object) {
        return 0;
    }

    @Override
    public int delete(Character object) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }
}
