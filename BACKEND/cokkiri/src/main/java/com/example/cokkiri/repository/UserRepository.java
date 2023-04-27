package com.example.cokkiri.repository;

import com.example.cokkiri.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByEmail(String email);
    public boolean existsByEmailAndAuthKey(String email,String authKey);
    public boolean existsByIdAndPasswordAndAuthTrue(String id,String password);
}
