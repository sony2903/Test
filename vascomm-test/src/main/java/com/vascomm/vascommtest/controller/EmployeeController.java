package com.vascomm.vascommtest.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vascomm.vascommtest.model.Mst_Employee;
import com.vascomm.vascommtest.model.ResponseMdl;
import com.vascomm.vascommtest.model.ResponseMdlPagination;
import com.vascomm.vascommtest.service.EmployeeService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @RequestMapping("/")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }

    // Create
    @PostMapping("")
    public ResponseEntity<ResponseMdl> createEmployee(@Valid @RequestBody Mst_Employee emp) {
        ResponseMdl response = new ResponseMdl();
        try {
            if (emp.getId() == null) emp.setId(service.employeeNextVal());
            Mst_Employee result = service.save(emp);
            response.setCode("201");
            response.setMessage(ResponseMdl.SUCCESS);
            response.setData(result);
            // Build the URI for the newly created resource
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(result.getId())
                    .toUri();
            return ResponseEntity.created(location).body(response);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseMdl> getEmployeeByCode(@RequestParam String employeeCode) {
        Mst_Employee employee = service.findByEmployeeCode(employeeCode);
        ResponseMdl response = new ResponseMdl();
        
        if (employee != null) {
            response.setCode("200");
            response.setMessage(ResponseMdl.SUCCESS);
            response.setData(employee);
            return ResponseEntity.ok(response);
        } else {
            response.setCode("404");
            response.setMessage("Employee not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseMdlPagination> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Mst_Employee> employees = service.findAllEmployeesWithPagination(page, size);
        ResponseMdlPagination response = new ResponseMdlPagination();

        response.setCode("200");
        response.setMessage(ResponseMdl.SUCCESS);
        response.setData(employees);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseMdl> deleteEmployeeByCode(@RequestParam String employeeCode) {
        Mst_Employee employee = service.softDeleteEmployee(employeeCode);
        ResponseMdl response = new ResponseMdl();
        
        if (employee != null) {
            response.setCode("200");
            response.setMessage(ResponseMdl.SUCCESS);
            response.setData(employee);
            return ResponseEntity.ok(response);
        } else {
            response.setCode("404");
            response.setMessage("Employee not found");
            return ResponseEntity.status(404).body(response);
        }
    }
    
}
