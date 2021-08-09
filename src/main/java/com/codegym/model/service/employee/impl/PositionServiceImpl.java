package com.codegym.model.service.employee.impl;

import com.codegym.model.entity.about_employee.Position;
import com.codegym.model.repository.emloyee.PositionRepository;
import com.codegym.model.service.employee.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }
}
