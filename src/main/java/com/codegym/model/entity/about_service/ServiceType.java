package com.codegym.model.entity.about_service;

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
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer serviceTypeId;

    private String serviceTypeName;

    @OneToMany(mappedBy = "serviceType")
    private Set<Service> services;
}
