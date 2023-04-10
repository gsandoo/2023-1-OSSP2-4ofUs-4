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
    private String student_id;

    //수업이면 true 공강이면 false
    @Column
    private boolean is_class;

    //희망 인원 수
    @Column
    private int total_people;

    //가능한 요일
    @ElementCollection
    private List<String> possible_day;

    @Column
    private Time start_time;

    @Column
    private Time end_time;

    //학수번호
    @Column
    private String subject_number;
}
