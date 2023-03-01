package com.example.moviecharactersapi.runners;

import com.example.moviecharactersapi.dto.FranchiseDTO;
import com.example.moviecharactersapi.mappers.FranchiseMapper;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import com.example.moviecharactersapi.repositories.MovieRepository;
import com.example.moviecharactersapi.services.FranchiseService;
import com.example.moviecharactersapi.services.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppRunner implements ApplicationRunner {

    private final FranchiseRepository franchiseRepository;
    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;
    private final MovieRepository movieRepository;
    private final MovieService movieService;

    public AppRunner(FranchiseRepository franchiseRepository, FranchiseService franchiseService, FranchiseMapper franchiseMapper, MovieRepository movieRepository, MovieService movieService) {
        this.franchiseRepository = franchiseRepository;
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
//        Collection<Franchise> franchises = franchiseService.findAll();
//        List<FranchiseDTO> franchiseDTOS = franchises.stream().map(franchise -> franchiseMapper.franchiseToFranchiseDTO(franchise)).collect(Collectors.toList());
//        for (int i = 0; i < franchiseDTOS.size(); i++) {
//            System.out.println(franchiseDTOS.get(i));
//        }
//        List<Franchise> franchises1 = franchiseDTOS.stream().map(franchiseDTO -> franchiseMapper.franchiseDTOToFranchise(franchiseDTO)).collect(Collectors.toList());
//        for (int i = 0; i < franchises1.size(); i++) {
//            for (Movie movie : franchises1.get(i).getMovies()) {
//                System.out.println(movie.getTitle());
//            }
//        }
    }


}
