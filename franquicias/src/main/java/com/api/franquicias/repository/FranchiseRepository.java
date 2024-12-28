package com.api.franquicias.repository;

import com.api.franquicias.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository< Franchise, Long> {
}
