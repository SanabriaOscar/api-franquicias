package com.api.franquicias.service.impl;


import com.api.franquicias.model.Product;
import com.api.franquicias.repository.BranchRepository;
import com.api.franquicias.repository.ProductRepository;
import com.api.franquicias.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl  implements IProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product saveProduct(Product product) {

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("The name field cannot be null or empty");
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
    }

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductWithMaxStockByBranch() {
        return productRepository.findProductosConMasStockPorBranch();
    }


}
