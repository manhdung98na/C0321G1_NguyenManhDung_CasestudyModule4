package com.codegym.dto;

import com.codegym.model.entity.about_customer.Customer;

import javax.persistence.OneToMany;
import java.util.Set;

public class CustomerTypeDTO {
    private Integer customerTypeId;
    private String customerTypeName;
}
