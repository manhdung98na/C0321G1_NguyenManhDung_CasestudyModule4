package com.codegym.controller;

import com.codegym.dto.ContractDetailDTO;
import com.codegym.model.entity.about_contract.AttachService;
import com.codegym.model.entity.about_contract.ContractDetail;
import com.codegym.model.service.contract.AttachServiceService;
import com.codegym.model.service.contract.ContractDetailService;
import com.codegym.model.service.contract.ContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contract-detail")
public class ContractDetailController {
    @Autowired
    private ContractDetailService contractDetailService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private AttachServiceService attachServiceService;

    @GetMapping("")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("contract_detail/list");
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("contract_detail/create");
        modelAndView.addObject("contractDetail", new ContractDetailDTO());
        modelAndView.addObject("listContract", contractService.findAll());
        modelAndView.addObject("listAttachService", attachServiceService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("contractDetail") ContractDetailDTO contractDetailDTO,
                         RedirectAttributes redirectAttributes, Model model){
        if (contractDetailDTO.getQuantity() > contractDetailDTO.getAttachService().getAttachServiceUnit()){
            model.addAttribute("listContract", contractService.findAll());
            model.addAttribute("listAttachService", attachServiceService.findAll());
            model.addAttribute("errorQuantity", "Can't be greater than Attach-service-unit");
            return "contract_detail/create";
        }
        ContractDetail contractDetail = new ContractDetail();
        BeanUtils.copyProperties(contractDetailDTO, contractDetail);
        contractDetailService.save(contractDetail);
        redirectAttributes.addFlashAttribute("status","Success");
        redirectAttributes.addFlashAttribute("statusContent","Success to create new Contract-detail!");
        return "redirect:/contract";
    }

}
