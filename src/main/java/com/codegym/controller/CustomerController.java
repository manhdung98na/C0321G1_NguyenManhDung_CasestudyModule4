package com.codegym.controller;

import com.codegym.dto.CustomerDTO;
import com.codegym.model.entity.about_customer.Customer;
import com.codegym.model.service.customer.CustomerService;
import com.codegym.model.service.customer.CustomerTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerTypeService customerTypeService;

    @GetMapping
    public ModelAndView showList(@PageableDefault(value = 3) Pageable pageable) {
        Page<Customer> list = customerService.findAll(pageable);
        return new ModelAndView("customer/list", "listCustomer", list);
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new CustomerDTO());
        modelAndView.addObject("customerType", customerTypeService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        new CustomerDTO().validate(customerDTO, bindingResult);
        if (!bindingResult.hasFieldErrors()) {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDTO, customer);
            customer.setDeleted(false);
            customerService.save(customer);
            redirectAttributes.addFlashAttribute("status", "Success!");
            return "redirect:/customer";
        } else {
            model.addAttribute("customerType", customerTypeService.findAll());
            return "customer/create";
        }
    }

    @GetMapping("/view/{id}")
    public ModelAndView showView(@PathVariable String id){
        CustomerDTO customerDTO = new CustomerDTO();
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()){
            return new ModelAndView("error");
        }
        ModelAndView modelAndView = new ModelAndView("customer/view");
                BeanUtils.copyProperties(customer.get(),customerDTO);
        modelAndView.addObject("customerType", customerTypeService.findAll());
        modelAndView.addObject("customer", customerDTO);
        return modelAndView;
    }
}
