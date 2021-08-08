package com.codegym.dto;

import com.codegym.model.entity.about_customer.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
