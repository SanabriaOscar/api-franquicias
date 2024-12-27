package com.api.franquicias.service;

import com.api.franquicias.model.Franchise;

public interface IFranchiseService {

    public Franchise saveFranchise(Franchise franchise);
    public Franchise findById(Long id);
}
