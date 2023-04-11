package com.example.cokkiri.controller;

import com.example.cokkiri.model.User;
import com.example.cokkiri.service.MailSendService;
import com.example.cokkiri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private MailSendService mss;
    @Autowired
    private UserService userService;

    //회원가입 처리 부분
    @GetMapping("/signup")
    public ResponseEntity <User> saveUser(User user){
        String authKey = mss.sendAuthMail(user.getEmail());
        user.setAuthKey(authKey);
        return new ResponseEntity<User>(userService.save(user),HttpStatus.OK);
    }

    //user가 인증메일을 눌렀을 떄 처리
    @GetMapping("/signUpConfirm")
    public ResponseEntity <String> signUpConfirm(@RequestParam(value = "email")String email){
        return new ResponseEntity<String>(email,HttpStatus.OK);
    }

}
