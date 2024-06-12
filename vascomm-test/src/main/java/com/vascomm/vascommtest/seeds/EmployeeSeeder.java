package com.vascomm.vascommtest.seeds;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.vascomm.vascommtest.model.Mst_Employee;
import com.vascomm.vascommtest.repo.EmployeeRepository;

@Component
public class EmployeeSeeder implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists to prevent duplicate seeding
        if (employeeRepository.count() == 0) {
            // Seed data
            Mst_Employee employee1 = new Mst_Employee();
            employee1.setId(1L);
            employee1.setEmployee_code("E001");
            employee1.setMobile_phone("1234567890");
            employee1.setActive_flg(1);
            employee1.setInsert_date(new Date());
            employee1.setAdmin_flag(false);
            
            Mst_Employee employee2 = new Mst_Employee();
            employee2.setId(2L);
            employee2.setEmployee_code("E002");
            employee2.setMobile_phone("0987654321");
            employee2.setActive_flg(1);
            employee2.setInsert_date(new Date());
            employee1.setAdmin_flag(false);

            employeeRepository.save(employee1);
            employeeRepository.save(employee2);

            System.out.println("Seeded employees.");
        } else {
            System.out.println("Employees already seeded.");
        }
    }
}
