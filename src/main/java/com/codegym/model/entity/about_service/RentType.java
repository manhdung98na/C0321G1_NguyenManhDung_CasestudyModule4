package com.codegym.model.entity.about_service;

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
public class RentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer rentTypeId;

    private String rentTypeName;

    private Double rentTypeCost;

    @OneToMany(mappedBy = "rentType")
    private Set<Service> services;
}
