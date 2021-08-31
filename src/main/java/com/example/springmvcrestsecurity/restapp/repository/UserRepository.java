package com.example.springmvcrestsecurity.restapp.repository;

import com.example.springmvcrestsecurity.restapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}
