package com.example.cokkiri.repository;

import com.example.cokkiri.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
