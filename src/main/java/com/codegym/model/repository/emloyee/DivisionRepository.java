package com.codegym.model.repository.emloyee;

import com.codegym.model.entity.about_employee.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DivisionRepository extends JpaRepository<Division, Integer> {
}
