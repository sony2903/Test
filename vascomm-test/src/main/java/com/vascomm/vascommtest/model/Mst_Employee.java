package com.vascomm.vascommtest.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_employee")
public class Mst_Employee extends BaseStandartModel {
	
	@Id
	private Long id;
	
    @NotBlank(message = "Employee code is required")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Employee code should contain only alphanumeric characters")
    private String employee_code;

    @NotBlank(message = "Mobile phone is required")
    @Pattern(regexp = "\\d{10,12}", message = "Mobile phone should be 10 to 12 digits long")
    private String mobile_phone;
    
	private boolean admin_flag = false;
	
}
