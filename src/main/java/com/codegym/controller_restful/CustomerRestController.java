package com.codegym.controller_restful;

import com.codegym.model.entity.about_customer.Customer;
import com.codegym.model.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customerDeleted = customer.get();
        customerDeleted.setDeleted(true);
        customerService.save(customerDeleted);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
