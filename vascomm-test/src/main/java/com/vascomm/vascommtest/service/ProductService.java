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

import com.vascomm.vascommtest.model.Mst_Product;
import com.vascomm.vascommtest.repo.EmployeeRepository;
import com.vascomm.vascommtest.repo.ProductRepository;

@Service
public class ProductService {
	@Autowired
    ProductRepository repo;

    public Mst_Product save(Mst_Product req) {
        if(req.getId() == null) req.setId(repo.count() == 0 ? 1 : repo.count() + 1);
        return repo.save(req);
    }

    public Mst_Product get(Long Id) {
        return repo.findById(Id).orElse(null);
    }

    public Mst_Product findBySku(String sku) {
        return repo.findBySKU(sku);
    }

    public Mst_Product softDelete(String sku){
        Mst_Product data = repo.findBySKU(sku);
        if(data != null && data.getDelete_flag() == 0){
            data.setDelete_flag(1);
            data.setDelete_date(new Date());
            data.setActive_flg(0);
            repo.save(data);
            return data;
        } else {
            return null;
        }
    }
    
    public Page<Mst_Product> pagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repo.findAll(pageRequest);
    }

}
