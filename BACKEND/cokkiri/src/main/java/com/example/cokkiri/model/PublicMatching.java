package com.example.cokkiri.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class PublicMatching {


    //매칭된 사람수
    private int headCount;

    //매칭된 시간
    private String matchingTime;

    //매칭된 사람들 학번
    private String studentId;

    //매칭 가능한 요일
    private  String availableDay;

    //약속시간
    private List<String> promiseTime;

    // 매칭 상태
    private boolean isPublicMatching;

    //매칭타입
    //공강=free , 수업=class
    private  String matchingType;

}
