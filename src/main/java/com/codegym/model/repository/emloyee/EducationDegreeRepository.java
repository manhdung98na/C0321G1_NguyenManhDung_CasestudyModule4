package com.codegym.model.repository.emloyee;

import com.codegym.model.entity.about_employee.EducationDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EducationDegreeRepository extends JpaRepository<EducationDegree, Integer> {
}
