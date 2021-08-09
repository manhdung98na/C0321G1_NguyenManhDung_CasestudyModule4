package com.codegym.model.service.employee;

import com.codegym.model.entity.about_employee.Employee;
import com.codegym.model.service.GeneralService;

public interface EmployeeService extends GeneralService<Employee, Integer> {
    String checkName(String name);

    String checkBirthday(String birthday);

    String checkIdCard(String idCard);

    String checkIdCard(String idCard, Integer id);

    String checkPhone(String phone);

    String checkPhone(String phone, Integer id);

    String checkEmail(String email);

    String checkEmail(String email, Integer id);
}
