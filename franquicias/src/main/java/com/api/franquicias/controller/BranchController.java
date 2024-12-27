package com.api.franquicias.controller;

import com.api.franquicias.model.Branch;
import com.api.franquicias.model.Franchise;
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
@RequestMapping("/api/branches")
public class BranchController {

    private static final Logger logger = LoggerFactory.getLogger(BranchController.class);


    @Autowired
    private IBranchService iBranchService;

    @Autowired
    private IProductService iProductService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createBranch(@Valid @RequestBody Branch branch, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        try {
            Branch savedbranch = iBranchService.saveBranch(branch);
            String successMessage = "Branch created sussefull";

            response.put("message", successMessage);
            response.put("branch", savedbranch);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            logger.error("Error saving branch.", e);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getBranchById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Branch branch = iBranchService.findById(id);
            response.put("branch", branch);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            logger.error("Error fetching branch by id.", e);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Agregar productos a una sucursal existente
    @PostMapping("/{branchId}/products")
    public ResponseEntity<Product> addProductToBranch(@PathVariable Long branchId, @RequestBody Product product) {
        Optional<Branch> branchOpt = Optional.ofNullable(iBranchService.findById(branchId));
        if (!branchOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Branch branch = branchOpt.get();
        product.setBranch(branch);
        Product savedProduct = iProductService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // Actualizar el stock
    @PutMapping("/{branchId}/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long branchId, @PathVariable Long productId, @RequestBody Product product) {
        Optional<Branch> branchOpt = Optional.ofNullable(iBranchService.findById(branchId));
        if (!branchOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        product.setBranch(branchOpt.get());
        Product updatedProduct = iProductService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }


    // Eliminar un producto de una susurcal
    @DeleteMapping("/{branchId}/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long branchId, @PathVariable Long productId) {
        Optional<Branch> branchOpt = Optional.ofNullable(iBranchService.findById(branchId));
        if (!branchOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        iProductService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
