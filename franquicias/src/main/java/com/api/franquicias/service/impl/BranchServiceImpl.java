package com.api.franquicias.service.impl;

import com.api.franquicias.model.Branch;
import com.api.franquicias.repository.BranchRepository;
import com.api.franquicias.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements IBranchService {


    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Branch saveBranch(Branch branch) {

        if (branch.getName() == null || branch.getName().isEmpty()) {
            throw new IllegalArgumentException("The name field cannot be null or empty");
        }
        return branchRepository.save(branch);
    }

    @Override
    public Branch findById(Long id) {
        return branchRepository.findById(id).orElseThrow(() -> new RuntimeException("Branch not found with id " + id));

    }
}
