package com.vascomm.vascommtest.controller;

import java.net.URI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.vascomm.vascommtest.model.Mst_Product;
import com.vascomm.vascommtest.model.ResponseMdlProduct;
import com.vascomm.vascommtest.model.ResponseMdlProductPagination;
import com.vascomm.vascommtest.service.ProductService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

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
    public ResponseEntity<ResponseMdlProduct> createProduct(@Valid @RequestBody Mst_Product emp) {
        ResponseMdlProduct response = new ResponseMdlProduct();
        try {
            boolean isId = emp.getId() == null;
            Mst_Product result = service.save(emp);
            response.setCode(isId ? "201" : "200");
            response.setMessage(ResponseMdlProduct.SUCCESS);
            response.setData(result);
            // Build the URI for the newly created resource
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(result.getId())
                    .toUri();
            return isId ? ResponseEntity.created(location).body(response) : ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseMdlProduct> getBySku(@RequestParam String sku) {
        Mst_Product data = service.findBySku(sku);
        ResponseMdlProduct response = new ResponseMdlProduct();
        
        if (data != null) {
            response.setCode("200");
            response.setMessage(ResponseMdlProduct.SUCCESS);
            response.setData(data);
            return ResponseEntity.ok(response);
        } else {
            response.setCode("404");
            response.setMessage("Product not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseMdlProductPagination> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Mst_Product> datas = service.pagination(page, size);
        ResponseMdlProductPagination response = new ResponseMdlProductPagination();

        response.setCode("200");
        response.setMessage(ResponseMdlProduct.SUCCESS);
        response.setData(datas);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseMdlProduct> deleteEmployeeByCode(@RequestParam String sku) {
        Mst_Product data = service.softDelete(sku);
        ResponseMdlProduct response = new ResponseMdlProduct();
        
        if (data != null) {
            response.setCode("200");
            response.setMessage(ResponseMdlProduct.SUCCESS);
            response.setData(data);
            return ResponseEntity.ok(response);
        } else {
            response.setCode("404");
            response.setMessage("Product not found");
            return ResponseEntity.status(404).body(response);
        }
    }
    
}
