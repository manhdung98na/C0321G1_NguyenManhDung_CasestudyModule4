package com.codegym.dto;

import com.codegym.model.entity.about_employee.Division;
import com.codegym.model.entity.about_employee.EducationDegree;
import com.codegym.model.entity.about_employee.Position;
import com.codegym.model.entity.about_employee.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Integer employeeId;
    private String employeeName;
    private String employeeBirthday;
    private String employeeIdCard;
    private Double employeeSalary;
    private String employeePhone;
    private String employeeEmail;
    private String employeeAddress;
    private Position position;
    private EducationDegree educationDegree;
    private Division division;
    private User username;
}
