package com.codegym.controller;

import com.codegym.dto.ContractDTO;
import com.codegym.dto.ContractDetailDTO;
import com.codegym.model.entity.about_contract.Contract;
import com.codegym.model.entity.about_contract.ContractDetail;
import com.codegym.model.service.contract.AttachServiceService;
import com.codegym.model.service.contract.ContractDetailService;
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
    @Autowired
    private ContractDetailService contractDetailService;
    @Autowired
    private AttachServiceService attachServiceService;

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

    @GetMapping("/contract-detail/{id}")
    public ModelAndView showAttachService(@PathVariable Integer id) {
        ModelAndView modelAndView;
        ContractDetailDTO contractDetailDTO = new ContractDetailDTO();
        Optional<Contract> contract = contractService.findById(id);
        if (!contract.isPresent()) {
            modelAndView = new ModelAndView("contract/list");
            modelAndView.addObject("status", "Fail");
            modelAndView.addObject("statusContent", "Invalid ID Contract!");
        }
        contractDetailDTO.setContract(contract.get());
        modelAndView = new ModelAndView("contract/add_contract_detail");
        modelAndView.addObject("contractDetail", contractDetailDTO);
        modelAndView.addObject("listContract", contractService.findAll());
        modelAndView.addObject("listAttachService", attachServiceService.findAll());
        return modelAndView;
    }

    @PostMapping("/contract-detail")
    public String addAttachService(@ModelAttribute("contractDetail") ContractDetailDTO contractDetailDTO,
                                   RedirectAttributes redirectAttributes, Model model) {
        ContractDetail contractDetail = new ContractDetail();
        BeanUtils.copyProperties(contractDetailDTO, contractDetail);
        if (contractDetailService.save(contractDetail) == null) {
            model.addAttribute("listContract", contractService.findAll());
            model.addAttribute("listAttachService", attachServiceService.findAll());
            model.addAttribute("errorQuantity", "Can't be greater than Attach-service-unit");
            return "contract/add_contract_detail";
        }

        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("statusContent", "Success to add AttachService!");
        return "redirect:/contract";
    }
}
