package com.codegym.dto;

import com.codegym.model.entity.about_contract.ContractDetail;
import com.codegym.model.entity.about_customer.Customer;
import com.codegym.model.entity.about_employee.Employee;
import com.codegym.model.entity.about_service.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {
    private Integer contractId;
    private String contractStartDate;
    private String contractEndDate;
    private Double contractDeposit;
    private Double contractTotalMoney;
    private Employee employee;
    private Customer customer;
    private Service service;
    private boolean isDelete;
    private Set<ContractDetail> contractDetail;
}
