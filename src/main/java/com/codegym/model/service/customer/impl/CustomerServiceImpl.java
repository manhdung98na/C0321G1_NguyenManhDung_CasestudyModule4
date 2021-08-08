package com.codegym.model.service.customer.impl;

import com.codegym.model.entity.about_customer.Customer;
import com.codegym.model.repository.customer.CustomerRepository;
import com.codegym.model.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public
class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public String checkId(String id) {
        String regexId = "^KH-[0-9]{4}$";
        if (!Pattern.matches(regexId, id)) {
            return "Invalid ID! Format: KH-xxxx";
        }
        if (customerRepository.existsById(id)) {
            return "Duplicated Id!";
        }
        return null;
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
        if (customerRepository.existsByCustomerIdCard(idCard)) {
            return "Duplicated ID Card!";
        }
        return null;
    }

    @Override
    public String checkIdCard(String idCard, String id) {
        String regexIdCard = "^([0-9]{9}|[0-9]{12})$";
        if (!Pattern.matches(regexIdCard, idCard)) {
            return "Invalid ID Card! Format: (9 or 12 digital characters)";
        }
        List<Customer> list = customerRepository.findAll();
        for (Customer obj : list) {
            if (!obj.getCustomerId().equals(id)) {
                if (idCard.equals(obj.getCustomerIdCard())) {
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
        if (customerRepository.existsByCustomerPhone(phone)) {
            return "Duplicated Phone number!";
        }
        return null;
    }

    @Override
    public String checkPhone(String phone, String id) {
        String regexPhone = "^(090|091|\\(84\\)\\+90|\\(84\\)\\+91)[0-9]{7}$";
        if (!Pattern.matches(regexPhone, phone)) {
            return "Invalid phone number! Format: 090xxxxxxx | 091xxxxxxx | (84)+90xxxxxxx | (84)+91xxxxxxx";
        }
        List<Customer> list = customerRepository.findAll();
        for (Customer obj : list) {
            if (!obj.getCustomerId().equals(id)) {
                if (phone.equals(obj.getCustomerPhone())) {
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
        if (customerRepository.existsByCustomerEmail(email)) {
            return "Duplicated Email!";
        }
        return null;
    }

    @Override
    public String checkEmail(String email, String id) {
        String regexEmail = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)";
        if (!Pattern.matches(regexEmail, email)) {
            return "Invalid email! Format: abc@xyz.zyt";
        }
        List<Customer> list = customerRepository.findAll();
        for (Customer obj : list) {
            if (!obj.getCustomerId().equals(id)) {
                if (email.equals(obj.getCustomerEmail())) {
                    return "Duplicated Email!";
                }
            }
        }
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(String id) {
        Customer customer = customerRepository.getById(id);
        customer.setDeleted(true);
        customerRepository.save(customer);
    }

    @Override
    public Page<Customer> search(String search, Pageable pageable) {
        return customerRepository.search(search, pageable);
    }
}
