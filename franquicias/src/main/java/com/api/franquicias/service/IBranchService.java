package com.api.franquicias.service;


import com.api.franquicias.model.Branch;

public interface IBranchService {

    public Branch saveBranch(Branch branch);
    Branch findById(Long id);
}
