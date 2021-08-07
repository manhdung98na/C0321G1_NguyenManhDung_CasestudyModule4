package com.codegym.model.repository.customer;

import com.codegym.model.entity.about_customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("select c from Customer c where c.isDeleted=false")
    Page<Customer> findAllByDeletedIsFalse(Pageable pageable);

    boolean existsByCustomerIdCard(String customerIdCard);

    boolean existsByCustomerPhone(String customerPhone);

    boolean existsByCustomerEmail(String customerEmail);

    @Query("select c from Customer c where (c.customerName like CONCAT('%',:search,'%') or c.customerId like CONCAT('%',:search,'%') or " +
            "c.customerIdCard like CONCAT('%',:search,'%') or c.customerEmail like CONCAT('%',:search,'%') or c.customerPhone like CONCAT('%',:search,'%')) " +
            "and c.isDeleted = false")
    Page<Customer> search(@Param("search") String search, Pageable pageable);
}
