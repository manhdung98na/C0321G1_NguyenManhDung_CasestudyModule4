package com.codegym.controller_restful;

import com.codegym.model.entity.about_service.Service;
import com.codegym.model.service.service.ServiceService;
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
@RequestMapping("/api/v1/service")
public class ServiceRestController {
    @Autowired
    private ServiceService service;

    @GetMapping
    public ResponseEntity<Page<Service>> findAll(@PageableDefault(value = 4) Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
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
            service.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check/id/{id}")
    public String checkId(@PathVariable String id) {
        return service.checkId(id);
    }

    @GetMapping("/check/name/{name}")
    public String checkName(@PathVariable String name) {
        return service.checkName(name);
    }

//    @GetMapping("/search")
//    public ResponseEntity<Page<Service>> search(@RequestParam String search, @PageableDefault(value = 4) Pageable pageable) {
//        return new ResponseEntity<>(service.search(search, pageable), HttpStatus.OK);
//    }
}
