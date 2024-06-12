package com.vascomm.vascommtest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vascomm.vascommtest.model.Mst_Product;

@Repository
public interface ProductRepository extends JpaRepository<Mst_Product, Long> {
    @Query("SELECT e FROM Mst_Product e WHERE e.sku = ?1")
    public Mst_Product findBySKU(String sku);

}
