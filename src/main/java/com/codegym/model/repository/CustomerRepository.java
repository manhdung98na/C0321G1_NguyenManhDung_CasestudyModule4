package com.codegym.model.repository;

import com.codegym.model.entity.about_customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("select c from Customer c where c.isDeleted=false")
    Page<Customer> findAllByDeletedIsFalse(Pageable pageable);
}
