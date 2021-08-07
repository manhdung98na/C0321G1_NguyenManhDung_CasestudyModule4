package com.codegym.dto;

import com.codegym.model.entity.about_customer.CustomerType;
import com.codegym.model.service.customer.CustomerService;
import com.codegym.model.service.customer.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String customerId;
    private CustomerType customerType;
    private String customerName;
    private String customerBirthday;
    private Byte customerGender;
    private String customerIdCard;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;
    private boolean isDeleted;
}
