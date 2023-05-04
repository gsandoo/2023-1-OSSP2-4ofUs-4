package com.example.cokkiri.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@ToString
public class ClassMatching {

    private String matchingId;

    //매칭된 사람수
    private int headCount;

    //매칭된 시간
    private String matchingTime;

    //매칭된 사람들 학번
    private String studentId;

    //학수번호
    private List<String> courseNumber;


    //약속시간
    private List<String> promiseTime;


    @ColumnDefault("0")
    //매칭 동의수 사람수랑 같게 되면 matchingRes=true
    private int matchingAgree;


    //매칭결과
    private boolean matchingRes;


    //매칭타입
    //공강=free , 수업=class
    private  String matchingType;

}
