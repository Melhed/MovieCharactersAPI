package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.dto.FranchiseDTO;
import com.example.moviecharactersapi.models.Franchise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {
    Franchise franchiseDTOToFranchise(FranchiseDTO franchiseDTO);
    FranchiseDTO franchiseToFranchiseDTO(Franchise franchise);

}
