package com.example.moviecharactersapi.services;

import java.util.Collection;

public interface CrudService <T, U> {
    T save(T object);
    T findById(U id);

    T add(T entity);

    Collection<T> findAll();
    T update(T entity);
    void delete(T entity);
    void deleteById(U id);
}

