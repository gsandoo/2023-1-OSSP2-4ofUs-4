package com.example.cokkiri.controller;

import com.example.cokkiri.model.User;
import com.example.cokkiri.service.MailSendService;
import com.example.cokkiri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private MailSendService mss;
    @Autowired
    private UserService userService;

    //회원가입 처리 부분
    @PostMapping("/signup")
    public ResponseEntity <User> saveUser(User user){
        //유저에게 메일을 보낸 인증키
        String authKey = mss.sendAuthMail(user.getEmail());
        user.setAuthKey(authKey);
        return new ResponseEntity<User>(userService.save(user),HttpStatus.OK);
    }

    //로그인 처리 부분
    @PostMapping("/login")
    public ResponseEntity <Boolean> login(String id,String password){
        return new ResponseEntity<Boolean>(userService.login(id,password),HttpStatus.OK);
    }

    //user가 인증메일을 눌렀을 떄 처리
    @GetMapping("/signUpConfirm")
    public ResponseEntity <String> signUpConfirm(@RequestParam(value = "email")String email,@RequestParam(value = "authKey")String authKey){

        //DB에 있는 authKey값과 맞는지 확인
        if(userService.checkAuthKey(email,authKey)){
            userService.updateAuth(email);
            return new ResponseEntity<String>("인증이 완료 되었습니다!!!",HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("잘못된 접근!",HttpStatus.OK);
    }



}
