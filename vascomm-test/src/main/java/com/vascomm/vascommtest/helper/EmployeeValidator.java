package com.vascomm.vascommtest.helper;

import com.vascomm.vascommtest.model.Mst_Employee;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Mst_Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mst_Employee employee = (Mst_Employee) target;

        // Validate employee code (alphanumeric)
        if (employee.getEmployee_code() != null && !employee.getEmployee_code().matches("^[a-zA-Z0-9]+$")) {
            errors.rejectValue("employee_code", "employee.employee_code.invalidFormat", "Employee code should contain only alphanumeric characters");
        }

        // Validate mobile phone (phone number format)
        if (employee.getMobile_phone() != null && !employee.getMobile_phone().matches("\\d{10,12}")) {
            errors.rejectValue("mobile_phone", "employee.mobile_phone.invalidFormat", "Mobile phone should be 10 to 12 digits long");
        }
    }


}
   