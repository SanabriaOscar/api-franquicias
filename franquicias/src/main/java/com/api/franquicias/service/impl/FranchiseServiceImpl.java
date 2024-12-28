package com.api.franquicias.service.impl;

import com.api.franquicias.model.Franchise;
import com.api.franquicias.repository.FranchiseRepository;
import com.api.franquicias.service.IFranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FranchiseServiceImpl implements IFranchiseService {
    @Autowired
    private FranchiseRepository franchiseRepository;



    @Override
    public Franchise saveFranchise(Franchise franchise) {
        if (franchise.getName() == null || franchise.getName().isEmpty()) {
            throw new IllegalArgumentException("The name field cannot be null or empty");
        }
         franchiseRepository.save(franchise);
        return franchise;
    }

    @Override
    public Franchise findById(Long id) {
        return franchiseRepository.findById(id) .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }
}
