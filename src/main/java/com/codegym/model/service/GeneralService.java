package com.codegym.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GeneralService<E, T> {
    Page<E> findAll(Pageable pageable);

    Optional<E> findById(T id);

    E save(E e);

    void delete(T id);

    Page<E> search(String search, Pageable pageable);
}
