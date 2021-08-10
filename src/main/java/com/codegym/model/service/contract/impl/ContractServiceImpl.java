package com.codegym.model.service.contract.impl;

import com.codegym.model.entity.about_contract.Contract;
import com.codegym.model.entity.about_contract.ContractDetail;
import com.codegym.model.repository.contract.ContractRepository;
import com.codegym.model.service.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository repository;

    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return repository.findAllByDeletedIsFalse(pageable);
    }


    @Override
    public List<Contract> findAll() {
        return repository.findAllByDeletedIsFalse();
    }

    @Override
    public Optional<Contract> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Contract save(Contract contract) {
        return repository.save(contract);
    }

    @Override
    public void delete(Integer id) {
        Contract contract = repository.getById(id);
        contract.setDeleted(true);
        repository.save(contract);
    }

    @Override
    public Page<Contract> search(String search, Pageable pageable) {
        return repository.search(search, pageable);
    }

    @Override
    public String checkStartDate(String date) {
        try {
            Date now = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            if (startDate.after(now)) {
                return "Must be a day in the past";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String checkEndDate(String endDate, String startDate) {
        try {
            Date endDateCheck = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            Date startDateCheck = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            if (!endDateCheck.after(startDateCheck)) {
                return "Must be a day after Start-date";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void calculateToTalMoney(ContractDetail contractDetail) {
        Contract contract = contractDetail.getContract();
        contract.setContractTotalMoney(contract.getContractTotalMoney() +
                contractDetail.getQuantity() * contractDetail.getAttachService().getAttachServiceCost());
        repository.save(contract);
    }
}
