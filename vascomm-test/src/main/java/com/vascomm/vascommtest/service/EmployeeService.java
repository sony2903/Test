package com.vascomm.vascommtest.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vascomm.vascommtest.model.Mst_Employee;
import com.vascomm.vascommtest.repo.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
    EmployeeRepository repo;

    public Mst_Employee save(Mst_Employee req) {
        return repo.save(req);
    }

    public Mst_Employee get(Long Id) {
        return repo.findById(Id).orElse(null);
    }

    public Mst_Employee softDeleteEmployee(String employeeCode){
        Mst_Employee employee = this.findByEmployeeCode(employeeCode);
        if(employee != null && employee.getDelete_flag() == 0){
            employee.setDelete_flag(1);
            employee.setDelete_date(new Date());
            employee.setActive_flg(0);
            repo.save(employee);
            return employee;
        } else {
            return null;
        }
    }

    public Long employeeNextVal(){
        return repo.employeeNextVal();
    }

    public Mst_Employee findByEmployeeCode(String employeeCode) {
        return repo.findByEmployeeCode(employeeCode);
    }
    
    public Page<Mst_Employee> findAllEmployeesWithPagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repo.findAll(pageRequest);
    }

}
