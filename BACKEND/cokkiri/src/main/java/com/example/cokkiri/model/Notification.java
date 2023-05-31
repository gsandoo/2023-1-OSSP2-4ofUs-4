package com.example.cokkiri.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    private String receiver; //알림을 받는 유저의 정보 , 이메일

    private String notificationType; //알림 종류별 분류를 위한

    private String content; //알람의 내용 -> "매칭이 성사 되었습니다 !"

    private String url; //해당 알림 클릭시 이동할 mapping url -> 채팅?

    private Boolean isRead; //알림 열람에 대한 여부
}
