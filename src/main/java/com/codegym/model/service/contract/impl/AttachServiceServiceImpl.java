package com.codegym.model.service.contract.impl;

import com.codegym.model.entity.about_contract.AttachService;
import com.codegym.model.repository.contract.AttachServiceRepository;
import com.codegym.model.service.contract.AttachServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachServiceServiceImpl implements AttachServiceService {
    @Autowired
    private AttachServiceRepository repository;

    @Override
    public List<AttachService> findAll() {
        return repository.findAllAvailable();
    }

    @Override
    public Page<AttachService> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<AttachService> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public AttachService save(AttachService attachService) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Page<AttachService> search(String search, Pageable pageable) {
        return null;
    }


}
