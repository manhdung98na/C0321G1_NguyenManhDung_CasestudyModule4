package com.codegym.model.service.employee;

import com.codegym.model.entity.about_employee.Position;

import java.util.List;

public interface PositionService {
    List<Position> findAll();
}
