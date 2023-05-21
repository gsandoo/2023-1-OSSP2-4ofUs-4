package com.example.cokkiri.controller;

import com.example.cokkiri.model.*;
import com.example.cokkiri.service.MatchingService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("matching")
public class MatchingController {
    @Autowired
    private  MatchingService matchingService;


    //데이터를 받아서 매치 타입 확인 후 match서비스로 연결 해준다.
    @PostMapping("/free")
    public ResponseEntity<PublicMatchedList> publicMatch(@RequestBody PublicMatching user){
        if(user.getMatchingType().equals("free")){
            return new ResponseEntity<>(matchingService.publicMatch(user), HttpStatus.OK);
        }
        else{
            System.out.println("잘못된 송출");
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }


    //데이터를 받아서 매치 타입 확인 후 match서비스로 연결 해준다.
    @PostMapping("/class")
    public ResponseEntity<ClassMatchedList> classMatch(@RequestBody ClassMatching user){
        if(user.getMatchingType().equals("class")) {
            return  new ResponseEntity<>(matchingService.classMatch(user), HttpStatus.OK);
        }else{
            System.out.println("잘못된 송출");
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    
    @GetMapping("agree/free")
    public ResponseEntity<MatchingAgree>  publicMatchingAgree(@RequestParam(value = "userId")String id){
        MatchingAgree agreeList = matchingService.publicMatchAgree(id);
        return new ResponseEntity<MatchingAgree>(agreeList,HttpStatus.OK);

    }
    @PutMapping("agree/class")
    public ResponseEntity<MatchingAgree> classMatchingAgree(@RequestParam(value = "userId")String id){
        MatchingAgree agreeList = matchingService.classMatchAgree(id);
        return new ResponseEntity<MatchingAgree>(agreeList,HttpStatus.OK);
    }



    @GetMapping("noshow/public")
    public ResponseEntity<List<NoShowPublicMatchList>> getPublicNoShowList(){
        List<NoShowPublicMatchList> noShowLists = matchingService.getNoShowPublicMatchList();
        return new ResponseEntity<List<NoShowPublicMatchList>>(noShowLists,HttpStatus.OK);
    } @GetMapping("noshow/class")
    public ResponseEntity<List<NoShowClassMatchList>> getClassNoShowList(){
        List<NoShowClassMatchList> noShowLists = matchingService.getNoShowClassMatchList();
        return new ResponseEntity<List<NoShowClassMatchList>>(noShowLists,HttpStatus.OK);
    }


    @PostMapping("post/declaration")
    public ResponseEntity<MatchDeclaration> postDeclaration(MatchDeclaration declaration){
        MatchDeclaration list = matchingService.postDeclarationList(declaration);
        return new ResponseEntity<MatchDeclaration>(list,HttpStatus.OK);
    }
    @GetMapping("get/declaration")
    public ResponseEntity<List<MatchDeclaration>> getDeclaration(){
        List<MatchDeclaration> list = matchingService.getDeclarationList();
        return new ResponseEntity<List<MatchDeclaration>>(list,HttpStatus.OK);

    }
}






