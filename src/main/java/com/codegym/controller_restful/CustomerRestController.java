package com.codegym.controller_restful;

import com.codegym.model.entity.about_customer.Customer;
import com.codegym.model.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(@PageableDefault(value = 4) Pageable pageable) {
        return new ResponseEntity<>(customerService.findAll(pageable), HttpStatus.OK);
    }

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

    @GetMapping("/check/id/{id}")
    public String checkId(@PathVariable String id) {
        return customerService.checkId(id);
    }

    @GetMapping("/check/name/{name}")
    public String checkName(@PathVariable String name) {
        return customerService.checkName(name);
    }

    @GetMapping("/check/idcard/{idcard}")
    public String checkIdCard(@PathVariable String idcard) {
        return customerService.checkIdCard(idcard);
    }

    @GetMapping("/check/update/idcard/{idcard}/{id}")
    public String checkUpdateIdCard(@PathVariable String idcard, @PathVariable String id) {
        return customerService.checkIdCard(idcard, id);
    }

    @GetMapping("/check/phone/{phone}")
    public String checkPhone(@PathVariable String phone) {
        return customerService.checkPhone(phone);
    }

    @GetMapping("/check/update/phone/{phone}/{id}")
    public String checkUpdatePhone(@PathVariable String phone, @PathVariable String id) {
        return customerService.checkPhone(phone, id);
    }

    @GetMapping("/check/email/{email}")
    public String checkEmail(@PathVariable String email) {
        return customerService.checkEmail(email);
    }

    @GetMapping("/check/update/email/{email}/{id}")
    public String checkUpdateEmail(@PathVariable String email, @PathVariable String id) {
        return customerService.checkEmail(email, id);
    }

    @GetMapping("/check/birthday/{birthday}")
    public String checkBirthday(@PathVariable String birthday) {
        return customerService.checkBirthday(birthday);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Customer>> search(@RequestParam String search, @PageableDefault(value = 4) Pageable pageable) {
        return new ResponseEntity<>(customerService.search(search, pageable), HttpStatus.OK);
    }
}
