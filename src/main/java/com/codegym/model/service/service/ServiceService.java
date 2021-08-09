package com.codegym.model.service.service;

import com.codegym.model.entity.about_service.Service;
import com.codegym.model.service.GeneralService;

import java.util.List;

public interface ServiceService extends GeneralService<Service, String> {
    List<Service> findAll();

    String checkId(String id);

    String checkName(String name);
}
