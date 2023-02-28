package com.example.moviecharactersapi.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CrudService <T, U> {

    T findById(U id);

    T add(T entity);

    List<T> findAll();
    T update(T entity);

    void deleteById(U id);
}

