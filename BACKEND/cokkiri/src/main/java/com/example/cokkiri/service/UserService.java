package com.example.cokkiri.service;

import com.example.cokkiri.model.User;
import com.example.cokkiri.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //AuthKey값 check함수
    public boolean checkAuthKey(String email,String authKey){
        return userRepository.existsByEmailAndAuthKey(email,authKey);
    }

    //user 회원정보 저장
    public User save(User user){
        userRepository.save(user);
        return user;
    }

    //메일 인증 성공시
    public void updateAuth(String email){
        User user = userRepository.findByEmail(email);
        user.setAuth(true);
        userRepository.save(user);
    }

    public boolean login(String id,String password){
        return userRepository.existsByIdAndPasswordAndAuthTrue(id,password);
    }
}
