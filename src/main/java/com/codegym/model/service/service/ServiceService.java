package com.codegym.model.service.service;

import com.codegym.model.entity.about_service.Service;
import com.codegym.model.service.GeneralService;

public interface ServiceService extends GeneralService<Service, String> {
    String checkId(String id);

    String checkName(String name);
}
