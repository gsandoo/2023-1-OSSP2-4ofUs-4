package com.example.cokkiri.service;

import com.example.cokkiri.model.User;
import com.example.cokkiri.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //모든 user반환
    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(e->users.add(e));
        return users;
    }
    public Optional<User> findById(String id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    //이름으로 조회하므로 null가능성 있음
    public Optional<User> findByName(String name){
        Optional<User> user = Optional.ofNullable(userRepository.findByName(name));
        return user;
    }

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

    //login
    public boolean login(String id,String password){
        return userRepository.existsByIdAndPasswordAndAuthTrue(id,password);
    }

    //user 탈퇴
    public void deleteById(String id){
        userRepository.deleteById(id);
    }

    //user update
    public void updateById(String id,User user){
        Optional<User> e = userRepository.findById(id);

        if(e.isPresent()){
            e.get().setPassword(user.getPassword());
            e.get().setMajor(user.getMajor());
            e.get().setNumber(user.getNumber());
            e.get().setCourse(user.getCourse());
            userRepository.save(e.get());
        }
    }
}
