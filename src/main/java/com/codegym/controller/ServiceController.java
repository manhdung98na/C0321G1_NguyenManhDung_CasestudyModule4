package com.codegym.controller;

import com.codegym.dto.ServiceDTO;
import com.codegym.model.entity.about_service.Service;
import com.codegym.model.service.service.RentTypeService;
import com.codegym.model.service.service.ServiceService;
import com.codegym.model.service.service.ServiceTypeService;
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
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService service;
    @Autowired
    private RentTypeService rentTypeService;
    @Autowired
    private ServiceTypeService serviceTypeService;

    @GetMapping("")
    public ModelAndView showList(@PageableDefault(value = 4) Pageable pageable) {
        Page<Service> list = service.findAll(pageable);
        return new ModelAndView("service/list", "listService", list);
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("service/create");
        modelAndView.addObject("service", new ServiceDTO());
        modelAndView.addObject("rentType", rentTypeService.findAll());
        modelAndView.addObject("serviceType", serviceTypeService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("service") ServiceDTO serviceDTO, RedirectAttributes redirectAttributes) {
        Service newService = new Service();
        BeanUtils.copyProperties(serviceDTO, newService);
        newService.setDeleted(false);
        if (service.save(newService) != null) {
            redirectAttributes.addFlashAttribute("status", "Success!");
            redirectAttributes.addFlashAttribute("statusContent", "Success to create customer " + newService.getServiceId());
        } else {
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Failure to create customer " + newService.getServiceId());
        }
        return "redirect:/service";
    }

    @GetMapping("/view/{id}")
    public ModelAndView showView(@PathVariable String id) {
        ServiceDTO serviceDto = new ServiceDTO();
        Optional<Service> viewService = service.findById(id);
        if (!viewService.isPresent()) {
            return new ModelAndView("error");
        }
        ModelAndView modelAndView = new ModelAndView("service/view");
        BeanUtils.copyProperties(viewService.get(), serviceDto);
        modelAndView.addObject("service", serviceDto);
        modelAndView.addObject("rentType", rentTypeService.findAll());
        modelAndView.addObject("serviceType", serviceTypeService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("service") ServiceDTO serviceDTO, RedirectAttributes redirectAttributes) {
        Service serviceEdit = new Service();
        BeanUtils.copyProperties(serviceDTO, serviceEdit);
        if (service.save(serviceEdit) != null) {
            redirectAttributes.addFlashAttribute("status", "Success!");
            redirectAttributes.addFlashAttribute("statusContent", "Success to update " + serviceEdit.getServiceId());
        } else {
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Failure to update " + serviceEdit.getServiceId());
        }
        return "redirect:/service";
    }

    @GetMapping("/search")
    public String search(@RequestParam("input-search") String search, @PageableDefault(value = 4) Pageable pageable, Model model) {
        Page<Service> list = service.search(search, pageable);
        model.addAttribute("listService", list);
        model.addAttribute("searchContent", search);
        return "service/search";
    }
}
