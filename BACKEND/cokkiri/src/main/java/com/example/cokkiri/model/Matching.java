package com.example.cokkiri.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Matching {
    //매칭 결과에 대한 Entity

    //매칭 번호 (auto increment)
    @Id
    @GeneratedValue
    @Column
    private String matchingId;

    //매칭된 사람수
    @Column
    private int totalPeople;

    //매칭된 시간
    @Column
    private Date matchingTime;

    //매칭된 사람들 학번
    @ElementCollection
    private List<String> users;

    //약속시간
    @Column
    private Date promiseTime;

    @Column
    @ColumnDefault("0")
    //매칭 동의수 사람수랑 같게 되면 matchingRes=true
    private int matchingAgree;

    @Column
    @ColumnDefault("false")
    //매칭결과
    private boolean matchingRes;




}
