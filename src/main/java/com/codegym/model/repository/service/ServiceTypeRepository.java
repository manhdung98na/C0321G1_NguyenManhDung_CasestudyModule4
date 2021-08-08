package com.codegym.model.repository.service;

import com.codegym.model.entity.about_service.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
}
