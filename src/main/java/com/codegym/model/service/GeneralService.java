package com.codegym.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GeneralService<E> {
    Page<E> findAll(Pageable pageable);

    Optional<E> findById(String id);

    E save(E e);

    void delete(String id);

    Page<E> search(String search, Pageable pageable);
}
