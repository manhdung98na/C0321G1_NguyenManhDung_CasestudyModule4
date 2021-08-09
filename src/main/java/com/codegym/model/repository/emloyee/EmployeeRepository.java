package com.codegym.model.repository.emloyee;

import com.codegym.model.entity.about_employee.Employee;
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
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee e where e.isDeleted=false")
    Page<Employee> findAllByDeletedIsFalse(Pageable pageable);

    @Query("select e from Employee e where e.isDeleted=false")
    List<Employee> findAllByDeletedIsFalse();

    boolean existsByEmployeePhone(String phone);

    boolean existsByEmployeeIdCard(String idCard);

    boolean existsByEmployeeEmail(String email);

    @Query("select e from Employee e where (e.employeeName like CONCAT('%',:search,'%') or " +
            "e.employeeIdCard like CONCAT('%',:search,'%') or e.employeeEmail like CONCAT('%',:search,'%') or e.employeePhone like CONCAT('%',:search,'%')) " +
            "and e.isDeleted = false")
    Page<Employee> search(@Param("search") String search, Pageable pageable);
}
