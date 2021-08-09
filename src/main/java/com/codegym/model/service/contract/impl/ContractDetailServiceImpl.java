package com.codegym.model.service.contract.impl;

import com.codegym.model.entity.about_contract.ContractDetail;
import com.codegym.model.repository.contract.ContractDetailRepository;
import com.codegym.model.service.contract.ContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractDetailServiceImpl implements ContractDetailService {
    @Autowired
    private ContractDetailRepository repository;

    @Override
    public Page<ContractDetail> findAll(Pageable pageable) {
        return repository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Optional<ContractDetail> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public ContractDetail save(ContractDetail contractDetail) {
        return repository.save(contractDetail);
    }

    @Override
    public void delete(Integer id) {
        ContractDetail contractDetail = repository.getById(id);
        contractDetail.setDeleted(true);
        repository.save(contractDetail);
    }

    @Override
    public Page<ContractDetail> search(String search, Pageable pageable) {
        return repository.search(search, pageable);
    }
}
