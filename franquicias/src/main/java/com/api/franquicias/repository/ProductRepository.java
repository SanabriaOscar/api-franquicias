package com.api.franquicias.repository;

import com.api.franquicias.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long > {

    @Query("SELECT p FROM Producto p WHERE p.stock = (SELECT MAX(p2.stock) FROM Producto p2 WHERE p2.branch = p.branch) ORDER BY p.branch.id")
    List<Product> findProductosConMasStockPorBranch();
}
