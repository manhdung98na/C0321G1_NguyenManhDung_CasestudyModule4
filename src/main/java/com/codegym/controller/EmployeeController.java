package com.codegym.controller;

import com.codegym.dto.EmployeeDTO;
import com.codegym.model.entity.about_employee.Employee;
import com.codegym.model.service.employee.DivisionService;
import com.codegym.model.service.employee.EducationDegreeService;
import com.codegym.model.service.employee.EmployeeService;
import com.codegym.model.service.employee.PositionService;
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
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DivisionService divisionService;
    @Autowired
    private EducationDegreeService educationDegreeService;

    @GetMapping("")
    public ModelAndView showList(@PageableDefault(value = 4) Pageable pageable) {
        Page<Employee> list = employeeService.findAll(pageable);
        return new ModelAndView("employee/list", "listEmployee", list);
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("employee/create");
        modelAndView.addObject("employee", new EmployeeDTO());
        modelAndView.addObject("position", positionService.findAll());
        modelAndView.addObject("division", divisionService.findAll());
        modelAndView.addObject("education", educationDegreeService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("employee") EmployeeDTO employeeDTO, RedirectAttributes redirectAttributes) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setDeleted(false);
        if (employeeService.save(employee) != null) {
            redirectAttributes.addFlashAttribute("status", "Success!");
            redirectAttributes.addFlashAttribute("statusContent", "Success to create employee " + employee.getEmployeeId());
        } else {
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Failure to create employee " + employee.getEmployeeId());
        }
        return "redirect:/employee";
    }

    @GetMapping("/view/{id}")
    public ModelAndView showView(@PathVariable Integer id) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            return new ModelAndView("error");
        }
        ModelAndView modelAndView = new ModelAndView("employee/view");
        BeanUtils.copyProperties(employee.get(), employeeDTO);
        modelAndView.addObject("employee", employeeDTO);
        modelAndView.addObject("position", positionService.findAll());
        modelAndView.addObject("division", divisionService.findAll());
        modelAndView.addObject("education", educationDegreeService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("employee") EmployeeDTO employeeDTO, RedirectAttributes redirectAttributes) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        if (employeeService.save(employee) != null) {
            redirectAttributes.addFlashAttribute("status", "Success!");
            redirectAttributes.addFlashAttribute("statusContent", "Success to update " + employee.getEmployeeId());
        } else {
            redirectAttributes.addFlashAttribute("status", "Fail!");
            redirectAttributes.addFlashAttribute("statusContent", "Failure to update " + employee.getEmployeeId());
        }
        return "redirect:/employee";
    }

    @GetMapping("/search")
    public String search(@RequestParam("input-search") String search, @PageableDefault(value = 4) Pageable pageable, Model model) {
        Page<Employee> list = employeeService.search(search, pageable);
        model.addAttribute("listEmployee", list);
        model.addAttribute("searchContent", search);
        return "employee/search";
    }
}
