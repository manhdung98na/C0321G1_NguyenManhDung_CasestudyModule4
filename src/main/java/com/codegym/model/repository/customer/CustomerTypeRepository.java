package com.codegym.model.repository.customer;

import com.codegym.model.entity.about_customer.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerTypeRepository extends JpaRepository<CustomerType, Integer> {
}
