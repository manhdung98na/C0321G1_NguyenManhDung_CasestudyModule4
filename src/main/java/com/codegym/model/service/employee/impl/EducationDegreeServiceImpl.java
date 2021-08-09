package com.codegym.model.service.employee.impl;

import com.codegym.model.entity.about_employee.EducationDegree;
import com.codegym.model.repository.emloyee.EducationDegreeRepository;
import com.codegym.model.service.employee.EducationDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationDegreeServiceImpl implements EducationDegreeService {
    @Autowired
    private EducationDegreeRepository repository;

    @Override
    public List<EducationDegree> findAll() {
        return repository.findAll();
    }
}
