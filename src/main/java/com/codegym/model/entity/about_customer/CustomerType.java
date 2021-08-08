package com.codegym.model.entity.about_customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerType {
    @Id
    private Integer customerTypeId;

    private String customerTypeName;

    @OneToMany(mappedBy = "customerType")
    private Set<Customer> customers;
}
