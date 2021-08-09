package com.codegym.model.service.contract;

import com.codegym.model.entity.about_contract.Contract;
import com.codegym.model.service.GeneralService;

import java.util.List;

public interface ContractService extends GeneralService<Contract, Integer> {
    List<Contract> findAll();

    String checkStartDate(String date);

    String checkEndDate(String endDate, String startDate);
}
