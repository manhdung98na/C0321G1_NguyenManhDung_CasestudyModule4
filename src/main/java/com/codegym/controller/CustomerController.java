package com.codegym.controller;

import com.codegym.dto.CustomerDTO;
import com.codegym.model.entity.about_contract.AttachService;
import com.codegym.model.entity.about_customer.Customer;
import com.codegym.model.service.contract.AttachServiceService;
import com.codegym.model.service.customer.CustomerService;
import com.codegym.model.service.customer.CustomerTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerTypeService customerTypeService;
    @Autowired
    private AttachServiceService attachServiceService;

    @GetMapping("")
    public ModelAndView showList(@PageableDefault(value = 4) Pageable pageable) {
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
    public String create(@ModelAttribute("customer") CustomerDTO customerDTO, RedirectAttributes redirectAttributes) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setDeleted(false);
        if (customerService.save(customer) != null) {
            redirectAttributes.addFlashAttribute("status", "Success!");
            redirectAttributes.addFlashAttribute("statusContent", "Success to create customer " + customer.getCustomerId());
        } else {
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Failure to create customer " + customer.getCustomerId());
        }
        return "redirect:/customer";
    }

    @GetMapping("/view/{id}")
    public ModelAndView showView(@PathVariable String id) {
        CustomerDTO customerDTO = new CustomerDTO();
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            return new ModelAndView("error");
        }
        ModelAndView modelAndView = new ModelAndView("customer/view");
        BeanUtils.copyProperties(customer.get(), customerDTO);
        modelAndView.addObject("customerType", customerTypeService.findAll());
        modelAndView.addObject("customer", customerDTO);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("customer") CustomerDTO customerDTO, RedirectAttributes redirectAttributes) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        if (customerService.save(customer) != null) {
            redirectAttributes.addFlashAttribute("status", "Success!");
            redirectAttributes.addFlashAttribute("statusContent", "Success to update " + customer.getCustomerId());
        } else {
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Failure to update " + customer.getCustomerId());
        }
        return "redirect:/customer";
    }

    @GetMapping("/search")
    public String search(@RequestParam("input-search") String search, @PageableDefault(value = 4) Pageable pageable, Model model) {
        Page<Customer> list = customerService.search(search, pageable);
        model.addAttribute("listCustomer", list);
        model.addAttribute("searchContent", search);
        return "customer/search";
    }

    @GetMapping("/using-service")
    public ModelAndView showCustomerUsingService() {
        ModelAndView modelAndView = new ModelAndView("customer/using-service");
        List<Customer> list = customerService.findCustomerUsingService();
        Map<Customer, List<AttachService>> map = new LinkedHashMap<>();
        for (Customer o : list) {
            List<AttachService> attachServices = attachServiceService.findAllByCustomerId(o.getCustomerId());
            map.put(o, attachServices);
        }
        modelAndView.addObject("listCustomerUsing", map);
        return modelAndView;
    }
}
