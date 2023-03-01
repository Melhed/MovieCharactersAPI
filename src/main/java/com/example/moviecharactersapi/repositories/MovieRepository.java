package com.example.moviecharactersapi.repositories;

import com.example.moviecharactersapi.models.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "select m from Movie m inner join Franchise f on m.franchise.id = f.id where f.id = ?1")
    Set<Movie> findMoviesByFranchiseId(Integer id);


}
