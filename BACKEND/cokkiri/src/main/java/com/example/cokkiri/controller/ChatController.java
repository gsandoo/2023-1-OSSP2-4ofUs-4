package com.example.cokkiri.controller;

import com.example.cokkiri.model.Chat;
import com.example.cokkiri.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    @Autowired
    private ChatService chatService;

    @MessageMapping("/{matchingId}") //여기로 전송되면 메서드 호출 -> WebSocketConfig prefixes 에서 적용한건 앞에 생략
    @SendTo("/room/{matchingId}")   //구독하고 있는 장소로 메시지 전송 (목적지)  -> WebSocketConfig Broker 에서 적용한건 앞에 붙어줘야됨
    public Chat chat(Integer matchingId, String matchingType, Chat chat) {

        //채팅 저장
        Chat res = chatService.save(chat);
        return res;

    }
}