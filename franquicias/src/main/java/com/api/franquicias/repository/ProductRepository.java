package com.api.franquicias.repository;

import com.api.franquicias.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long > {

    @Query(value = "SELECT * FROM producto p " +
            "WHERE p.stock = (SELECT MAX(p2.stock) FROM producto p2 WHERE p2.branch_id = p.branch_id) " +
            "ORDER BY p.branch_id", nativeQuery = true)
    List<Product> findProductosConMasStockPorBranch();

}
