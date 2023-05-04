package com.example.cokkiri.controller;

import com.example.cokkiri.model.User;
//import com.example.cokkiri.service.MailSendService;
import com.example.cokkiri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

//    @Autowired
//    private MailSendService mss;
//    @Autowired
//    private UserService userService;
//
//    //회원가입 처리 부분
//    @PostMapping("/signup")
//    public ResponseEntity <User> saveUser(User user){
//        //유저에게 메일을 보낸 인증키
//        String authKey = mss.sendAuthMail(user.getEmail());
//        user.setAuthKey(authKey);
//        return new ResponseEntity<User>(userService.save(user),HttpStatus.OK);
//    }
//
//
//
//    //user가 인증메일을 눌렀을 떄 처리
//    @GetMapping("/signUpConfirm")
//    public ResponseEntity <String> signUpConfirm(@RequestParam(value = "email")String email,@RequestParam(value = "authKey")String authKey){
//
//        //DB에 있는 authKey값과 맞는지 확인
//        if(userService.checkAuthKey(email,authKey)){
//            userService.updateAuth(email);
//            return new ResponseEntity<String>("인증이 완료 되었습니다!!!",HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<String>("잘못된 접근!",HttpStatus.OK);
//    }
//
//    @GetMapping ("/admin/user")
//    public ResponseEntity<List <User>> getAllUser(){
//        //관리자페이지에서 모든 유저 확인
//        List<User> users = userService.findAll();
//        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
//    }
//
//    @GetMapping ("/admin/user")
//    public ResponseEntity<User> getUserId(@RequestParam(value="userId")String id){
//        //관리자페이지에서 학번으로 유저 조회
//        Optional<User> user = userService.findById(id);
//        return new ResponseEntity<User>(user.get(),HttpStatus.OK);
//    }
//
//    @GetMapping ("/admin/user")
//    public ResponseEntity<User> getUserName(@RequestParam(value="userName")String name){
//        //관리자페이지에서 이름으로 유저 조회
//        Optional<User> user = userService.findByName(name);
//        return new ResponseEntity<User>(user.get(),HttpStatus.OK);
//    }
//
//    //유저 삭제
//    @DeleteMapping(value = "/admin/user/{userId}")
//    public ResponseEntity<User> deleteRoom(@PathVariable("userId") String id){
//        userService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    //학번으로 회원 정보 수정
//    @PutMapping(value = {"userMypage/{userId}"})
//    public ResponseEntity<User> updateRoom(@PathVariable("userId") String userId,User user){
//        userService.updateById(userId,user);
//        return new ResponseEntity<User>(user,HttpStatus.OK);
//    }



}
