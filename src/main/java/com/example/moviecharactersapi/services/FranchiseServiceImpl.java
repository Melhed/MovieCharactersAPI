package com.example.moviecharactersapi.services;

import com.example.moviecharactersapi.mappers.FranchiseMapper;
import com.example.moviecharactersapi.models.entity.Franchise;
import com.example.moviecharactersapi.models.entity.Movie;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FranchiseServiceImpl implements FranchiseService{
    private final FranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;
    private final MovieService movieService;

    /**
     * Gets franchise based on ID
     * @param franchiseId   ID of franchise
     * @return              franchise
     */
    @Override
    public Franchise findById(Integer franchiseId) {
        if(franchiseId == null) return null;
        return franchiseRepository.findById(franchiseId).get();
    }

    /**
     * Gets all franchises
     * @return  collection of all franchises
     */
    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    /**
     * Adds franchise
     * @param   franchise   franchise to be added
     * @return              added franchise
     */
    @Override
    public Franchise add(Franchise franchise) {
        if(franchise == null) return null;
        return franchiseRepository.save(franchise);
    }

    /**
     * Updates franchise
     * @param   franchise franchise to be updated
     * @return            updated franchise
     */
    @Override
    public Franchise update(Franchise franchise) {
        if(franchise == null) return null;
        return franchiseRepository.save(franchise);
    }

    /**
     * Updates movies in franchise
     * @param   franchiseId ID of franchise to update
     * @param   movieIds    IDs of movies to be in franchise
     * @return              updated franchise
     */
    @Override
    public Franchise updateMoviesInFranchise(Integer franchiseId, List<Integer> movieIds) {
        if(movieIds == null) return null;
        Franchise franchise = franchiseRepository.findById(franchiseId).get();
        Set<Movie> franchiseMovies = franchise.getMovies();

        // Removes franchise from movies no longer in franchise
        for (Movie franchiseMovie : franchiseMovies) {
            if(movieIds.indexOf(franchiseMovie.getId()) == -1) {
                franchiseMovie.setFranchise(null);
                movieService.update(franchiseMovie);
            }
        }

        // Adds franchise to movies not previously in franchise
        Set<Movie> moviesToAdd = movieIds.stream().map(movieId -> movieService.findById(movieId)).collect(Collectors.toSet());
        for (Movie movie : moviesToAdd) {
            if(movie.getFranchise() == null || movie.getFranchise().getId() != franchise.getId()) {
                movie.setFranchise(franchise);
                movieService.update(movie);
            }
        }

        franchise.setMovies(moviesToAdd);
        return franchiseRepository.save(franchise);
    }

    /**
     * Checks if franchise exists by ID
     * @param   franchiseId ID of franchise
     * @return              boolean indicating if franchise exists
     */
    @Override
    public boolean franchiseExistsById(Integer franchiseId) {
        return franchiseRepository.existsById(franchiseId);
    }

    /**
     * Deletes franchise
     * @param franchise franchise to be deleted
     */
    @Override
    public void delete(Franchise franchise) {
        franchiseRepository.deleteById(franchise.getId());
    }

    /**
     *  Deletes franchise based on ID
     *  @param franchiseId  ID of franchise to be deleted
     */
    @Override
    public void deleteById(Integer franchiseId) {
        if(franchiseId == null) return;
        Set<Movie> franchiseMovies = movieService.findMoviesInFranchise(franchiseId);

        for (Movie franchiseMovie : franchiseMovies) {
            franchiseMovie.setFranchise(null);
            movieService.update(franchiseMovie);
        }

        franchiseRepository.deleteById(franchiseId);
    }
}
