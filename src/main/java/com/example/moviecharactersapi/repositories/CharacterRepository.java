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
                characterList.add(new Character(result.getInt("character_id"),
                        result.getString("character_name"),
                        result.getString("character_alias"),
                        result.getString("character_gender"),
                        result.getString("character_picture_url")
                        ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return characterList;
    }

    @Override
    public Character findById(Integer id) {
        if(id == null) return null;
        Character character = null;
        String sql = "SELECT character_id, character_alias, character_gender, character_name, character_picture_url FROM tb_character WHERE character_id = ?";

        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()) {
                character = new Character(
                        result.getInt("character_id"),
                        result.getString("character_name"),
                        result.getString("character_alias"),
                        result.getString("character_gender"),
                        result.getString("character_picture_url")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return character;
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
