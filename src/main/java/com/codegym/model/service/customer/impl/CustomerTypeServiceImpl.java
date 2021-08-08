package com.codegym.model.service.customer.impl;

import com.codegym.model.entity.about_customer.CustomerType;
import com.codegym.model.repository.customer.CustomerTypeRepository;
import com.codegym.model.service.customer.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {
    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public List<CustomerType> findAll() {
        return customerTypeRepository.findAll();
    }

    @Override
    public Optional<CustomerType> findById(int id) {
        return customerTypeRepository.findById(id);
    }
}
