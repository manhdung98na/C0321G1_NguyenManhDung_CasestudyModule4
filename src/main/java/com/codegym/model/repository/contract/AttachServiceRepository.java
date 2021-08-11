package com.codegym.model.repository.contract;

import com.codegym.model.entity.about_contract.AttachService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AttachServiceRepository extends JpaRepository<AttachService, Integer> {
    @Query("select a from AttachService a where a.attachServiceUnit > 0")
    List<AttachService> findAllAvailable();

    @Query("select cd.attachService from ContractDetail cd where cd.contract.customer.customerId = :customerId and cd.isDeleted = false and cd.contract.isDeleted = false and  cd.contract.customer.isDeleted = false")
    List<AttachService> findAllByCustomerId(String customerId);
}
