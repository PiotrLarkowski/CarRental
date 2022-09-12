package com.example.carrental.repository;

import com.example.carrental.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserById(Long id);

    void deleteUserById(Long id);
}
