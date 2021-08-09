package com.codegym.controller_restful;

import com.codegym.model.entity.about_employee.Employee;
import com.codegym.model.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<Employee>> findAll(@PageableDefault(value = 4) Pageable pageable) {
        return new ResponseEntity<>(employeeService.findAll(pageable), HttpStatus.OK);
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
            employeeService.delete(Integer.parseInt(id));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check/name/{name}")
    public String checkName(@PathVariable String name) {
        return employeeService.checkName(name);
    }

    @GetMapping("/check/idcard/{idcard}")
    public String checkIdCard(@PathVariable String idcard) {
        return employeeService.checkIdCard(idcard);
    }

    @GetMapping("/check/update/idcard/{idcard}/{id}")
    public String checkUpdateIdCard(@PathVariable String idcard, @PathVariable Integer id) {
        return employeeService.checkIdCard(idcard, id);
    }

    @GetMapping("/check/phone/{phone}")
    public String checkPhone(@PathVariable String phone) {
        return employeeService.checkPhone(phone);
    }

    @GetMapping("/check/update/phone/{phone}/{id}")
    public String checkUpdatePhone(@PathVariable String phone, @PathVariable Integer id) {
        return employeeService.checkPhone(phone, id);
    }

    @GetMapping("/check/email/{email}")
    public String checkEmail(@PathVariable String email) {
        return employeeService.checkEmail(email);
    }

    @GetMapping("/check/update/email/{email}/{id}")
    public String checkUpdateEmail(@PathVariable String email, @PathVariable Integer id) {
        return employeeService.checkEmail(email, id);
    }

    @GetMapping("/check/birthday/{birthday}")
    public String checkBirthday(@PathVariable String birthday) {
        return employeeService.checkBirthday(birthday);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Employee>> search(@RequestParam String search, @PageableDefault(value = 4) Pageable pageable) {
        return new ResponseEntity<>(employeeService.search(search, pageable), HttpStatus.OK);
    }
}
