package com.codegym.model.repository.contract;

import com.codegym.model.entity.about_contract.ContractDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ContractDetailRepository extends JpaRepository<ContractDetail, Integer> {
    @Query("select c from ContractDetail c where c.isDeleted = false and c.contract.isDeleted = false")
    Page<ContractDetail> findAllByDeletedIsFalse(Pageable pageable);

    @Query("select c from ContractDetail c where (c.attachService.attachServiceName like CONCAT('%',:search,'%') " +
            "or c.contract.contractId = :search) and c.isDeleted = false")
    Page<ContractDetail> search(@Param("search") String search, Pageable pageable);
}
