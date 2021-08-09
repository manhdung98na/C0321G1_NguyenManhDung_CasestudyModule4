package com.codegym.model.repository.service;

import com.codegym.model.entity.about_service.Service;
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
public interface ServiceRepository extends JpaRepository<Service, String> {
    @Query("select s from Service s where s.isDeleted=false")
    Page<Service> findAllByDeletedIsFalse(Pageable pageable);

    @Query("select s from Service s where s.isDeleted=false")
    List<Service> findAllByDeletedIsFalse();

    @Query("select s from Service s where (s.serviceName like CONCAT('%',:search,'%') or s.serviceId like CONCAT('%',:search,'%')" +
            " or s.descriptionOtherConvenience like CONCAT('%',:search,'%') or s.standardRoom like CONCAT('%',:search,'%') " +
            "or s.serviceType.serviceTypeName like CONCAT('%',:search,'%')) and s.isDeleted = false")
    Page<Service> search(@Param("search") String search, Pageable pageable);
}
