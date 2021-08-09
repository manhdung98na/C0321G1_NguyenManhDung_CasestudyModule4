package com.codegym.model.service.contract;

import com.codegym.model.entity.about_contract.AttachService;
import com.codegym.model.service.GeneralService;

import java.util.List;

public interface AttachServiceService extends GeneralService<AttachService, Integer> {
    List<AttachService> findAll();
}
