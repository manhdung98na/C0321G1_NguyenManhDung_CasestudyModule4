package com.codegym.controller_restful;

import com.codegym.model.entity.about_service.Service;
import com.codegym.model.service.contract.ContractService;
import com.codegym.model.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/contract")
public class ContractRestController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/check/start-date/{startDate}")
    public String checkStartDate(@PathVariable String startDate) {
        return contractService.checkStartDate(startDate);
    }

    @GetMapping("/check/end-date/{endDate}/{startDate}")
    public String checkEndDate(@PathVariable String endDate, @PathVariable String startDate) {
        return contractService.checkEndDate(endDate, startDate);
    }

    @GetMapping("/get-total-money/{id}")
    public ResponseEntity<String> getTotalMoney(@PathVariable String id) {
        Optional<Service> service = serviceService.findById(id);
        if (service.isPresent()) {
            return new ResponseEntity<>(service.get().getServiceCost().toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/multi-delete")
    public ResponseEntity<Void> deleteMulti(@RequestBody String data) {
        String idStringConCat = "";
        Pattern p = Pattern.compile(":\\[(.*?)]}");
        Matcher m = p.matcher(data);
        while (m.find()) {
            idStringConCat = m.group(1);
        }
        String[] idArray = idStringConCat.replace("\"", "").split(",");
        for (String id : idArray) {
            contractService.delete(Integer.parseInt(id));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
