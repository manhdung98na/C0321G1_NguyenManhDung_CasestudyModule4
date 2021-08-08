package com.codegym.model.service.service.impl;

import com.codegym.model.repository.service.ServiceRepository;
import com.codegym.model.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Page<com.codegym.model.entity.about_service.Service> findAll(Pageable pageable) {
        return serviceRepository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Optional<com.codegym.model.entity.about_service.Service> findById(String id) {
        return serviceRepository.findById(id);
    }

    @Override
    public com.codegym.model.entity.about_service.Service save(com.codegym.model.entity.about_service.Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void delete(String id) {
        com.codegym.model.entity.about_service.Service serviceObj = serviceRepository.getById(id);
        serviceObj.setDeleted(true);
        serviceRepository.save(serviceObj);
    }

    @Override
    public Page<com.codegym.model.entity.about_service.Service> search(String search, Pageable pageable) {
        return serviceRepository.search(search, pageable);
    }

    @Override
    public String checkId(String id) {
        String regexId = "^DV-[0-9]{4}$";
        if (!Pattern.matches(regexId, id)) {
            return "Invalid ID! Format: DV-xxxx";
        }
        if (serviceRepository.existsById(id)) {
            return "Duplicated Id!";
        }
        return null;
    }

    @Override
    public String checkName(String name) {
        String regexName = "^\\p{Lu}\\p{Ll}*(\\s\\p{Ll}*[0-9]*)*$";
        if (!Pattern.matches(regexName, name)) {
            return "Invalid name! Need to upper case FIRST CHARACTER";
        }
        return null;
    }
}
