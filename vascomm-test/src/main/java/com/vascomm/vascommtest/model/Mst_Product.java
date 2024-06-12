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
@Table(name = "mst_product")
public class Mst_Product extends BaseStandartModel {
	
	@Id
	private Long id;
	
    @NotBlank(message = "sku is required")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Employee code should contain only alphanumeric characters")
    private String sku;

    // @NotBlank(message = "quantity is required")
    private Integer quantity;
    
	
}
