package com.example.carrental.repository;

import com.example.carrental.domain.Income.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    Income findIncomeById(Long id);
}
