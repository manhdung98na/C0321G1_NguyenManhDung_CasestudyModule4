package com.codegym.model.repository.contract;

import com.codegym.model.entity.about_contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    @Query("select c from Contract c where c.isDeleted=false")
    Page<Contract> findAllByDeletedIsFalse(Pageable pageable);

    @Query("select c from Contract c where c.isDeleted=false")
    List<Contract> findAllByDeletedIsFalse();

    @Query("select c from Contract c where (c.customer.customerName like CONCAT('%',:search,'%') or c.service.serviceName like CONCAT('%',:search,'%') or " +
            "c.employee.employeeName like CONCAT('%',:search,'%') or c.contractStartDate like CONCAT('%',:search,'%') or c.contractEndDate like CONCAT('%',:search,'%')) " +
            "and c.isDeleted = false")
    Page<Contract> search(@Param("search") String search, Pageable pageable);
}
