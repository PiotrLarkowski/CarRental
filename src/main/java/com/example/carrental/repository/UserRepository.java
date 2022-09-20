package com.example.carrental.repository;

import com.example.carrental.domain.User.CarRentalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<CarRentalUser, Long> {
    CarRentalUser findUserById(Long id);
}
