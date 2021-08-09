package com.codegym.controller;

import com.codegym.dto.ContractDTO;
import com.codegym.model.entity.about_contract.Contract;
import com.codegym.model.service.contract.ContractService;
import com.codegym.model.service.customer.CustomerService;
import com.codegym.model.service.employee.EmployeeService;
import com.codegym.model.service.service.ServiceService;
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

import java.util.Optional;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ContractService contractService;

    @GetMapping("")
    public ModelAndView showList(@PageableDefault(value = 4) Pageable pageable) {
        Page<Contract> list = contractService.findAll(pageable);
        return new ModelAndView("contract/list", "listContract", list);
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("contract/create");
        modelAndView.addObject("contract", new ContractDTO());
        modelAndView.addObject("listCustomer", customerService.findAll());
        modelAndView.addObject("listService", serviceService.findAll());
        modelAndView.addObject("listEmployee", employeeService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("contract") ContractDTO contractDTO, RedirectAttributes redirectAttributes) {
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDTO, contract);
        if (contractService.save(contract) != null) {
            redirectAttributes.addFlashAttribute("status", "Success!");
            redirectAttributes.addFlashAttribute("statusContent", "Success to create new Contract");
        } else {
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Failure to create new Contract");
        }
        return "redirect:/contract";
    }

    @GetMapping("/view/{id}")
    public ModelAndView showView(@PathVariable Integer id) {
        ContractDTO contractDTO = new ContractDTO();
        Optional<Contract> contractOptional = contractService.findById(id);
        if (!contractOptional.isPresent()) {
            return new ModelAndView("error");
        }
        ModelAndView modelAndView = new ModelAndView("contract/view");
        BeanUtils.copyProperties(contractOptional.get(), contractDTO);
        modelAndView.addObject("contract", contractDTO);
        modelAndView.addObject("listCustomer", customerService.findAll());
        modelAndView.addObject("listService", serviceService.findAll());
        modelAndView.addObject("listEmployee", employeeService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("employee") ContractDTO contractDTO, RedirectAttributes redirectAttributes) {
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDTO, contract);
        if (contractService.save(contract) != null) {
            redirectAttributes.addFlashAttribute("status", "Success!");
            redirectAttributes.addFlashAttribute("statusContent", "Success to update contract");
        } else {
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Failure to update contract");
        }
        return "redirect:/contract";
    }

    @GetMapping("/search")
    public String search(@RequestParam("input-search") String search, @PageableDefault(value = 4) Pageable pageable, Model model) {
        Page<Contract> list = contractService.search(search, pageable);
        model.addAttribute("listContract", list);
        model.addAttribute("searchContent", search);
        return "contract/search";
    }
}
