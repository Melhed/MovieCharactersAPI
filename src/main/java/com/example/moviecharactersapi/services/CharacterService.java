package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.controllers.CharacterDTO;
import com.example.moviecharactersapi.mappers.CharacterDTOMapper;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterDTOMapper characterDTOMapper;

    public CharacterService(CharacterRepository characterRepository,
                            CharacterDTOMapper characterDTOMapper) {
        this.characterRepository = characterRepository;
        this.characterDTOMapper = characterDTOMapper;
    }

    public List<CharacterDTO> findAllCharacters() {
        return characterRepository.findAll().stream().map(characterDTOMapper).toList();
    }

    public CharacterDTO findCharacterById(Integer characterId) {
        if(characterId == null) return null;
        return characterDTOMapper.apply(characterRepository.findById(characterId));
    }

}
