package com.api.franquicias.service;

import com.api.franquicias.model.Product;

import java.util.List;

public interface IProductService {

    public Product saveProduct(Product product);
    public void deleteProduct(Long productId);
    public Product findProductById(Long productId);
    Product updateProduct(Long id, Product product);
    public List<Product> getProductWithMaxStockByBranch();

}
