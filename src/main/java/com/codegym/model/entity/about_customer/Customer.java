package com.codegym.model.entity.about_customer;

import com.codegym.model.entity.about_contract.Contract;
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
public class Customer {
    @Id
    private String customerId;

    @ManyToOne(targetEntity = CustomerType.class)
    @JoinColumn(name = "customer_type_id", referencedColumnName = "customerTypeId")
    private CustomerType customerType;

    private String customerName;

    @Column(name = "customer_birthday", columnDefinition = "DATE")
    private String customerBirthday;

    private Byte customerGender;

    private String customerIdCard;

    private String customerPhone;

    private String customerEmail;

    private String customerAddress;

    private boolean isDeleted;

    @OneToMany(mappedBy = "customer")
    private Set<Contract> contract;
}
