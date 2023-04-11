package com.example.cokkiri.service;

import com.example.cokkiri.model.User;
import com.example.cokkiri.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        userRepository.save(user);
        return user;
    }
}
