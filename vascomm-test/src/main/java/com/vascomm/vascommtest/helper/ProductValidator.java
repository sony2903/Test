package com.vascomm.vascommtest.helper;

import com.vascomm.vascommtest.model.Mst_Employee;
import com.vascomm.vascommtest.model.Mst_Product;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Mst_Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mst_Product data = (Mst_Product) target;

        if (data.getSku() != null && !data.getSku().matches("^[a-zA-Z0-9]+$")) {
            errors.rejectValue("sku", "data.sku.invalidFormat", "sku should contain only alphanumeric characters");
        }

    }


}
   