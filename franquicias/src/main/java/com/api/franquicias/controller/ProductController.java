package com.api.franquicias.controller;

import com.api.franquicias.model.Branch;
import com.api.franquicias.model.Product;
import com.api.franquicias.service.IBranchService;
import com.api.franquicias.service.IProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(BranchController.class);


    @Autowired
    private IProductService iProductService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createProduct(@Valid @RequestBody Product product, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        try {
            Product savedProduct = iProductService.saveProduct(product);
            String successMessage = "Product created sussefull";

            response.put("message", successMessage);
            response.put("product", savedProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            logger.error("Error saving product.", e);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        Map<String, Object> response = new HashMap<>();
        try {
            iProductService.deleteProduct(productId);
            response.put("message", "Product deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            logger.error("Error deleting product.", e);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Product product = iProductService.findProductById(productId);
            response.put("product", product);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            logger.error("Error finding product.", e);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/max-stock")
    public ResponseEntity<Product> getProductWithMaxStock() {
        Product product = (Product) iProductService.getProductWithMaxStockByBranch();
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    

}
