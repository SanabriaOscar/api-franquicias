package com.api.franquicias.controller;

import com.api.franquicias.model.Branch;
import com.api.franquicias.model.Franchise;
import com.api.franquicias.service.IFranchiseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/frachises")

public class FranchiseController {
    private static final Logger logger = LoggerFactory.getLogger(FranchiseController.class);


@Autowired
    private IFranchiseService iFranchiseService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createFranchise(@Valid @RequestBody Franchise franchise, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        try {
            Franchise savedFranchise = iFranchiseService.saveFranchise(franchise);
            String successMessage = "Franchise created sussefull";

            response.put("message", successMessage);
            response.put("franchise", savedFranchise);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            logger.error("Error saving franchise.", e);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getFranchseById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Franchise franchise = iFranchiseService.findById(id);
            response.put("franchise", franchise);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            logger.error("Error fetching franchise by id.", e);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
