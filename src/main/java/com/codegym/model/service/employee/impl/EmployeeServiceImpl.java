package com.codegym.model.service.employee.impl;

import com.codegym.model.entity.about_employee.Employee;
import com.codegym.model.repository.emloyee.EmployeeRepository;
import com.codegym.model.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return repository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void delete(Integer id) {
        Employee employee = repository.getById(id);
        employee.setDeleted(true);
        repository.save(employee);
    }

    @Override
    public Page<Employee> search(String search, Pageable pageable) {
        return repository.search(search, pageable);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAllByDeletedIsFalse();
    }

    @Override
    public String checkName(String name) {
        String regexName = "^\\p{Lu}\\p{Ll}*(\\s\\p{Lu}\\p{Ll}*)*$";
        if (!Pattern.matches(regexName, name)) {
            return "Invalid name! Need to upper case EACH FIRST CHARACTER";
        }
        return null;
    }

    @Override
    public String checkBirthday(String birthday) {
        String result = "Birthday must be a day in the past!";
        String[] dates = birthday.split("-");
        String currentDate = LocalDate.now().toString();
        String[] currentDateArr = currentDate.split("-");
        if (Integer.parseInt(dates[0]) > Integer.parseInt(currentDateArr[0])) {
            return result;
        }
        if (Integer.parseInt(dates[0]) == Integer.parseInt(currentDateArr[0])) {
            if (Integer.parseInt(dates[1]) > Integer.parseInt(currentDateArr[1])) {
                return result;
            }
            if (Integer.parseInt(dates[1]) == Integer.parseInt(currentDateArr[1])) {
                if (Integer.parseInt(dates[2]) > Integer.parseInt(currentDateArr[2])) {
                    return result;
                }
            }
        }
        return null;
    }

    @Override
    public String checkIdCard(String idCard) {
        String regexIdCard = "^([0-9]{9}|[0-9]{12})$";
        if (!Pattern.matches(regexIdCard, idCard)) {
            return "Invalid ID Card! Format: (9 or 12 digital characters)";
        }
        if (repository.existsByEmployeeIdCard(idCard)) {
            return "Duplicated ID Card!";
        }
        return null;
    }

    @Override
    public String checkIdCard(String idCard, Integer id) {
        String regexIdCard = "^([0-9]{9}|[0-9]{12})$";
        if (!Pattern.matches(regexIdCard, idCard)) {
            return "Invalid ID Card! Format: (9 or 12 digital characters)";
        }
        List<Employee> list = repository.findAll();
        for (Employee obj : list) {
            if (!obj.getEmployeeId().equals(id)) {
                if (idCard.equals(obj.getEmployeeIdCard())) {
                    return "Duplicated ID Card!";
                }
            }
        }
        return null;
    }

    @Override
    public String checkPhone(String phone) {
        String regexPhone = "^(090|091|\\(84\\)\\+90|\\(84\\)\\+91)[0-9]{7}$";
        if (!Pattern.matches(regexPhone, phone)) {
            return "Invalid phone number! Format: 090xxxxxxx | 091xxxxxxx | (84)+90xxxxxxx | (84)+91xxxxxxx";
        }
        if (repository.existsByEmployeePhone(phone)) {
            return "Duplicated Phone number!";
        }
        return null;
    }

    @Override
    public String checkPhone(String phone, Integer id) {
        String regexPhone = "^(090|091|\\(84\\)\\+90|\\(84\\)\\+91)[0-9]{7}$";
        if (!Pattern.matches(regexPhone, phone)) {
            return "Invalid phone number! Format: 090xxxxxxx | 091xxxxxxx | (84)+90xxxxxxx | (84)+91xxxxxxx";
        }
        List<Employee> list = repository.findAll();
        for (Employee obj : list) {
            if (!obj.getEmployeeId().equals(id)) {
                if (phone.equals(obj.getEmployeePhone())) {
                    return "Duplicated Phone number!";
                }
            }
        }
        return null;
    }

    @Override
    public String checkEmail(String email) {
        String regexEmail = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)";
        if (!Pattern.matches(regexEmail, email)) {
            return "Invalid email! Format: abc@xyz.zyt";
        }
        if (repository.existsByEmployeeEmail(email)) {
            return "Duplicated Email!";
        }
        return null;
    }

    @Override
    public String checkEmail(String email, Integer id) {
        String regexEmail = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)";
        if (!Pattern.matches(regexEmail, email)) {
            return "Invalid email! Format: abc@xyz.zyt";
        }
        List<Employee> list = repository.findAll();
        for (Employee obj : list) {
            if (!obj.getEmployeeId().equals(id)) {
                if (email.equals(obj.getEmployeeEmail())) {
                    return "Duplicated Email!";
                }
            }
        }
        return null;
    }
}
