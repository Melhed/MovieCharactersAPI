package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FranchiseServiceImpl implements FranchiseService{
    private final FranchiseRepository franchiseRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
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
