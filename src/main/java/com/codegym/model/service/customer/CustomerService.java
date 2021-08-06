package com.codegym.model.service.customer;

import com.codegym.model.entity.about_customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    void save(Customer customer);

    Optional<Customer> findById(String id);

    void delete(String id);
}
