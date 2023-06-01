package com.example.cokkiri.controller;

import com.example.cokkiri.model.*;
import com.example.cokkiri.service.MailSendService;
import com.example.cokkiri.service.MatchingService;


import com.example.cokkiri.service.NoShowMailSendService;
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
    @Autowired
    private  NoShowMailSendService noShowMailSendService;
    @Autowired
    private MailSendService mss;


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

    // 매치된 리스트 삭제
    @GetMapping("delete/free")
    public  ResponseEntity<String> deletePublucMatch(@RequestParam(value = "matchingId")int id){
        return new ResponseEntity<String>(matchingService.deletePublicUser(id),HttpStatus.OK);
    }
    @GetMapping("delete/class")
    public ResponseEntity<String> deleteClassMatch(@RequestParam(value = "matchingId")int id){
        return new ResponseEntity<String>(matchingService.deleteClassUser(id),HttpStatus.OK);
    }


    @PutMapping("agree/free")
    public ResponseEntity<String> publicMatchingAgree(@RequestParam(value = "matchingId")int matchingId , @RequestParam(value = "userId") String id){
        String comment = matchingService.publicMatchAgree(matchingId , id);
        return new ResponseEntity<String>(comment,HttpStatus.OK);

    }
    @PutMapping("agree/class")
    public ResponseEntity<String> classMatchingAgree(@RequestParam(value = "matchingId")int matchingId,@RequestParam(value = "userId")String id){
        String comment = matchingService.classMatchAgree(matchingId,id);
        return new ResponseEntity<String>(comment,HttpStatus.OK);
    }



    @GetMapping("get/noshow/public")
    public ResponseEntity<List<NoShowPublicMatchList>> getPublicNoShowList(){
        List<NoShowPublicMatchList> noShowLists = matchingService.getNoShowPublicMatchList();
        return new ResponseEntity<List<NoShowPublicMatchList>>(noShowLists,HttpStatus.OK);
    } @GetMapping("get/noshow/class")
    public ResponseEntity<List<NoShowClassMatchList>> getClassNoShowList(){
        List<NoShowClassMatchList> noShowLists = matchingService.getNoShowClassMatchList();
        return new ResponseEntity<List<NoShowClassMatchList>>(noShowLists,HttpStatus.OK);
    }

    //노쇼의심유저에게 메일 보내기
    @GetMapping("send/noshow")
    public ResponseEntity<String> sendEmailToNoShowUser(@RequestParam(value = "userId")String id , @RequestParam(value = "matchingType")String matchingType){
        String send = noShowMailSendService.sendAuthMail(id, matchingType);
        return new ResponseEntity<String>(send, HttpStatus.OK);
    }

    // 노쇼 등록
    @PostMapping("post/noshow/public")
    public ResponseEntity<NoShowPublicMatchList> postNoShowPublicList(@RequestBody NoShowPublicMatchList user){
        NoShowPublicMatchList noShowUser = matchingService.postNoShowPublicUser(user);
        return  new ResponseEntity<NoShowPublicMatchList>(noShowUser, HttpStatus.OK);
    }

    @PostMapping("post/noshow/class")
    public ResponseEntity<NoShowClassMatchList> postNoShowClassList(@RequestBody NoShowClassMatchList user){
        NoShowClassMatchList noShowUser = matchingService.postNoShowClassUser(user);
        return  new ResponseEntity<NoShowClassMatchList>(noShowUser, HttpStatus.OK);
    }
    // 신고 등록
    @PostMapping("post/accusation")
    public ResponseEntity<MatchAccusation> postAccusation(@RequestBody MatchAccusation declaration){
        MatchAccusation list = matchingService.postDeclarationList(declaration);
        return new ResponseEntity<MatchAccusation>(list,HttpStatus.OK);
    }

    // 신고 목록리스트(수업/공강) 조회
    @GetMapping("get/accusation")
    public ResponseEntity<List<MatchAccusation>> getAccusation(@RequestParam(value = "matchingType")String matchingType){
        List<MatchAccusation> list = matchingService.getDeclarationList(matchingType);
        return new ResponseEntity<List<MatchAccusation>>(list,HttpStatus.OK);
    }

    // 특정 공강 매칭 신고조회
    @GetMapping("get/publicmatch/accusation")
    public ResponseEntity<MatchAccusation> getPublicAccusation(@RequestParam(value = "matchingId")int id , @RequestParam(value = "matchingType")String matchingType){
        if(matchingType.equals("free")){
            MatchAccusation list = matchingService.getPublicDeclarationList(id, matchingType);
            return  new ResponseEntity<MatchAccusation>(list,HttpStatus.OK);
        }else {
            return  new ResponseEntity<MatchAccusation>(HttpStatus.BAD_REQUEST);
        }

    }
    //특정 수업 매칭 신고조회
    @GetMapping("get/classmatch/accusation")
    public ResponseEntity<MatchAccusation> getClassAccusation(@RequestParam(value = "matchingId")int id , @RequestParam(value = "matchingType")String matchingType){
        if(matchingType.equals("class")){
            MatchAccusation list = matchingService.getClassDeclarationList(id, matchingType);
            return  new ResponseEntity<MatchAccusation>(list,HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<MatchAccusation>(HttpStatus.BAD_REQUEST);
        }
    }

    // 노쇼 발생 시 하트 반환
    @PutMapping("rollback/heart")
    public ResponseEntity<User> rollbackHeart(){

        return new ResponseEntity<>(HttpStatus.OK);
    }


    //관리자페이지에서 매칭id로 매칭중 리스트 반환
    @GetMapping("admin/publicMatchedList")
    public ResponseEntity<PublicMatchedList> findPublicMatchedListByEmail(@RequestParam (value = "matchingId")int id){
        PublicMatchedList matchList =  matchingService.findPublicMatchingByMatchId(id);
        return  new ResponseEntity<PublicMatchedList>(matchList , HttpStatus.OK);
    }

    @GetMapping("admin/classMatchedList")
    public ResponseEntity<ClassMatchedList> findClassMatchedListByEmail(@RequestParam (value = "matchingId")int id){
        ClassMatchedList matchList =  matchingService.findClassMatchingByMatchId(id);
        return  new ResponseEntity<ClassMatchedList>(matchList , HttpStatus.OK);
    }

    @GetMapping("admin/matchingWait")
    public ResponseEntity<List<PublicMatchingWait>> getAllMatchingWait(){
        List<PublicMatchingWait> list = matchingService.findAllMatchingWait();
        return  new ResponseEntity<List<PublicMatchingWait>>(list, HttpStatus.OK);
    }

    @GetMapping("get/matchingWait")
    public ResponseEntity<List<PublicMatchingWait>> getMatchingWaitById(@RequestParam (value = "email")String id){
        List<PublicMatchingWait> list = matchingService.findMatchingWaitByEmail(id);
        return  new ResponseEntity<List<PublicMatchingWait>>(list, HttpStatus.OK);
    }
    @GetMapping("delete/free/matchingWait")
    public ResponseEntity<PublicMatchingWait> deletePublicMatchingWait(@RequestParam (value = "waitId")int id){
        PublicMatchingWait deletedUser = matchingService.deletePublicMatchingWaitById(id);
        return new ResponseEntity<PublicMatchingWait>(deletedUser, HttpStatus.OK);
    }

    @GetMapping("delete/class/matchingWait")
    public ResponseEntity<ClassMatchingWait> deleteClassMatchingWait(@RequestParam (value = "waitId")int id){
        ClassMatchingWait deletedUser = matchingService.deleteClassMatchingWaitById(id);
        return new ResponseEntity<ClassMatchingWait>(deletedUser, HttpStatus.OK);
    }
}






