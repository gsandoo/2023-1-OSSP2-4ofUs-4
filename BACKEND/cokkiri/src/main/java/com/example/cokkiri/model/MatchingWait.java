package com.example.cokkiri.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MatchingWait {

    @Id
    @GeneratedValue
    private int id; // 번호

    @Column
    private String email; // 사용자 이메일

    @Column
    private String matchingType; // 매칭타입

    @Column
    private String status; // 매칭 대기
}
