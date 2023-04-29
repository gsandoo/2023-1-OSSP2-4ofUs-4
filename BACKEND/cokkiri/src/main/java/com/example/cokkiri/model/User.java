package com.example.cokkiri.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // pk 반드시 필요
public class User {

    @Id             // pk
    @GeneratedValue // 자동으로 값 증가
    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String sex;

    @Column
    private String major;

    @Column
    private String number;

    @Column
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime restrctionDate;

    @ElementCollection
    private List<String> course;

    @ColumnDefault("false")
    @Column
    private boolean admin;

    //인증여부
    @ColumnDefault("false")
    @Column
    private boolean auth;

    @Column
    private String authKey;
}
