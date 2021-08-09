package com.codegym.model.service.employee.impl;

import com.codegym.model.entity.about_employee.Division;
import com.codegym.model.repository.emloyee.DivisionRepository;
import com.codegym.model.service.employee.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionServiceImpl implements DivisionService {
    @Autowired
    private DivisionRepository repository;

    @Override
    public List<Division> findAll() {
        return repository.findAll();
    }
}
