package com.example.cokkiri.controller;

import com.example.cokkiri.model.ClassMatchedList;
import com.example.cokkiri.model.Payment;
import com.example.cokkiri.model.PublicMatchedList;
import com.example.cokkiri.model.User;
//import com.example.cokkiri.service.MailSendService;
import com.example.cokkiri.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private MailSendService mss;
    @Autowired
    private UserService userService;

    @Autowired
    private MatchingService matchingService;

    @Autowired
    private PaymentService paymentService;

    //로그인 처리 부분
    @PostMapping("/login")
    public ResponseEntity <Boolean> login(String id,String password){
        return new ResponseEntity<Boolean>(userService.login(id,password),HttpStatus.OK);
    }

    //관리자 로그인
    @PostMapping("/loginAdmin")
    public ResponseEntity <Boolean> loginAdmin(String id,String password){
        return new ResponseEntity<Boolean>(userService.loginAdmin(id,password),HttpStatus.OK);
    }

    //회원가입 처리 부분
    @PostMapping("/signup")
    public ResponseEntity <User> saveUser(User user){
        //유저에게 메일을 보낸 인증키
        String authKey = mss.sendAuthMail(user.getId());
        user.setAuthKey(authKey);
        return new ResponseEntity<User>(userService.save(user),HttpStatus.OK);
    }



    //user가 인증메일을 눌렀을 떄 처리
    @GetMapping("/signUpConfirm")
    public ResponseEntity <String> signUpConfirm(@RequestParam(value = "id")String id,@RequestParam(value = "authKey")String authKey){

        //DB에 있는 authKey값과 맞는지 확인
        if(userService.checkAuthKey(id,authKey)){
            userService.updateAuth(id);
            return new ResponseEntity<String>("인증이 완료 되었습니다!!!",HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("잘못된 접근!",HttpStatus.OK);
    }

    @GetMapping ("/admin/classMatching")
    public ResponseEntity<List <ClassMatchedList>> getAllClassMatching(){
        //관리자페이지에서 모든 수업 매칭 확인
        List<ClassMatchedList> matchedLists = matchingService.findAllClassMatching();
        return new ResponseEntity<List<ClassMatchedList>>(matchedLists,HttpStatus.OK);
    }

    @GetMapping ("/admin/publicMatching")
    public ResponseEntity<List <PublicMatchedList>> getAllPublicMatching(){
        //관리자페이지에서 모든 수업 매칭 확인
        List<PublicMatchedList> matchedLists = matchingService.findAllPublicMatching();
        return new ResponseEntity<List<PublicMatchedList>>(matchedLists,HttpStatus.OK);
    }

    @GetMapping ("/admin/user")
    public ResponseEntity<List <User>> getAllUser(){
        //관리자페이지에서 모든 유저 확인
        List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @GetMapping ("/admin/payment")
    public ResponseEntity<List <Payment>> getAllPayment(){
        //관리자페이지에서 모든 결제내역 확인
        List<Payment> payments = paymentService.findAll();
        return new ResponseEntity<List<Payment>>(payments,HttpStatus.OK);
    }

    @GetMapping ("/admin/user/id")
    public ResponseEntity<User> getUserId(@RequestParam(value="userId")String id){
        //관리자페이지에서 이메일으로 유저 조회 (마이페이지에서도 이걸 사용할 것)
        Optional<User> user = userService.findById(id);
        return new ResponseEntity<User>(user.get(),HttpStatus.OK);
    }
    

    @GetMapping ("/admin/user/name")
    public ResponseEntity<List<User>> getUserName(@RequestParam(value="userName")String name){
        //관리자페이지에서 이름으로 유저 조회
        List<User> users = userService.findByName(name);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @GetMapping ("/admin/user/payment")
    public ResponseEntity<List<Payment>> getUserPayment(@RequestParam(value="userId")String id){
        //관리자페이지에서 이름으로 유저 결제내역 조회
        List<Payment> payments = paymentService.findById(id);
        return new ResponseEntity<List<Payment>>(payments,HttpStatus.OK);
    }



    //유저 삭제
    @DeleteMapping(value = "/admin/user/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") String id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //이메일으로 관리자가 회원 정보 수정
    @PutMapping(value = {"admin/user/{userId}"})
    public ResponseEntity<User> updateUserAdmin(@PathVariable("userId") String userId,User user){
        userService.updateByIdAdmin(userId,user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }


    //이메일으로 회원 정보 수정
    @PutMapping(value = {"userMypage/{userId}"})
    public ResponseEntity<User> updateUser(@PathVariable("userId") String userId,User user){
        userService.updateById(userId,user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    //이메일로 결제내역 저장 및 쿠키개수 변경
    @PutMapping(value = {"payment"})
    public ResponseEntity<User> updateUserHeart(@PathVariable("userId") int heart, Payment payment){
        paymentService.save(payment);
        userService.updateById(payment.getUserId(),heart);
        return new ResponseEntity<User>(userService.findById(payment.getUserId()).get(),HttpStatus.OK);
    }

}
