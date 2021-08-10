package com.codegym.model.entity.about_contract;

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
public class AttachService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attachServiceId;

    private String attachServiceName;

    private Double attachServiceCost;

    private Integer attachServiceUnit;

    private String attachServiceStatus;

    @OneToMany(mappedBy = "attachService")
    private Set<ContractDetail> contractDetail;
}
