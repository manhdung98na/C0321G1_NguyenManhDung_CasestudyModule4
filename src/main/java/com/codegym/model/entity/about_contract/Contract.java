package com.codegym.model.entity.about_contract;

import com.codegym.model.entity.about_customer.Customer;
import com.codegym.model.entity.about_employee.Employee;
import com.codegym.model.entity.about_service.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;

    @Column(name = "contract_start_date", columnDefinition = "DATE")
    private String contractStartDate;

    @Column(name = "contract_end_date", columnDefinition = "DATE")
    private String contractEndDate;

    private Double contractDeposit;

    private Double contractTotalMoney;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
    private Employee employee;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;

    @ManyToOne(targetEntity = Service.class)
    @JoinColumn(name = "service_id", referencedColumnName = "serviceId")
    private Service service;

    private boolean isDeleted;

    @OneToMany(mappedBy = "contract")
    private Set<ContractDetail> contractDetail;
}
