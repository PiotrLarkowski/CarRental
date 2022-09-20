package com.example.carrental.repository;

import com.example.carrental.domain.RentalBranch.RentalBranch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalBranchRepository extends JpaRepository<RentalBranch, Long> {
    RentalBranch findRentalBranchById(Long id);

}
