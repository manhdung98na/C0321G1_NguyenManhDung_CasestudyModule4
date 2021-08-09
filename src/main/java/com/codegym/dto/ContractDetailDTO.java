package com.codegym.dto;

import com.codegym.model.entity.about_contract.AttachService;
import com.codegym.model.entity.about_contract.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContractDetailDTO {
    private Integer contractDetailId;
    private Contract contract;
    private AttachService attachService;
    private Integer quantity;
    private boolean isDeleted;
}
