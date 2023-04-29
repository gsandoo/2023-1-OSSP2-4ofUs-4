package com.example.cokkiri.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Matching {

    @Id
    @GeneratedValue
    @Column
    private String matchingId;

    //희망 인원 수
    @Column
    private int totalPeople;

    //가능한 요일
    @ElementCollection
    private List<String> possibleDay;


    @Column
    private Time startTime;

    @Column
    private Time endTime;

    //학수번호 null이면 공강매칭 null이 아니면 수업매칭
    @Column
    private String subjectNumber;
}
