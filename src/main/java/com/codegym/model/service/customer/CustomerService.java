package com.codegym.model.service.customer;

import com.codegym.model.entity.about_customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Optional<Customer> findById(String id);

    String checkId(String id);

    String checkName(String name);

    String checkBirthday(String birthday);

    String checkIdCard(String idCard);

    String checkIdCard(String idCard, String id);

    String checkPhone(String phone);

    String checkPhone(String phone, String id);

    String checkEmail(String email);

    String checkEmail(String email, String id);

    Customer save(Customer customer);

    void delete(String id);

    Page<Customer> search(String search, Pageable pageable);
}
