package com.vascomm.vascommtest.repo;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vascomm.vascommtest.model.Mst_Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Mst_Employee, Long> {

    @Query(value = "select nextval('mst_employee_seq')", nativeQuery = true)
    public Long employeeNextVal();

    @Query("SELECT e FROM Mst_Employee e WHERE e.employee_code = ?1")
    public Mst_Employee findByEmployeeCode(String employeeCode);

}
