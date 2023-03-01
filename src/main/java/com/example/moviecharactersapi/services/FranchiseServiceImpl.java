package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.dto.FranchiseDTO;
import com.example.moviecharactersapi.mappers.FranchiseMapper;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class FranchiseServiceImpl implements FranchiseService{
    private final FranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, FranchiseMapper franchiseMapper) {
        this.franchiseRepository = franchiseRepository;
        this.franchiseMapper = franchiseMapper;
    }

    @Override
    public Franchise findById(Integer franchiseId) {
        if(franchiseId == null) return null;
        return franchiseRepository.findById(franchiseId).get();
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise add(Franchise franchise) {
        if(franchise == null) return null;
        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise update(Franchise franchise) {
        if(franchise == null) return null;
        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise updateFranchiseMoviesById(int id, List<Integer> movieIds) {
        if(movieIds == null) return null;
        FranchiseDTO franchiseDTO = franchiseMapper.franchiseToFranchiseDTO(franchiseRepository.findById(id).get());
        franchiseDTO.setMovieIds(movieIds);
        Franchise franchise = franchiseMapper.franchiseDTOToFranchise(franchiseDTO);
        return franchiseRepository.save(franchise);
    }

    @Override
    public void deleteById(Integer franchiseId) {
        if(franchiseId == null) return;
        franchiseRepository.deleteById(franchiseId);
    }

    @Override
    public void delete(Franchise franchise) {
        if(franchise == null) return;
        franchiseRepository.delete(franchise);
    }
}
