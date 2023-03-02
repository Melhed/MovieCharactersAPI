package com.example.moviecharactersapi.exceptions;

public class CharacterNotFoundException extends RuntimeException{

    public CharacterNotFoundException(int id) {
        super("Character does not exist with ID: " + id);
    }



}
