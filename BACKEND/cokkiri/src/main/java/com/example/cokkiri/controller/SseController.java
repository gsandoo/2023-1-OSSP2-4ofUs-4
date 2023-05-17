package com.example.cokkiri.controller;

import com.example.cokkiri.model.ClassMatching;
import com.example.cokkiri.model.PublicMatching;
import org.springframework.stereotype.Controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Controller
public class SseController {

    private Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    // sse 연결
    @GetMapping("/sse")
    public @ResponseBody
    void handleSseRequest(@RequestParam(value = "id" ,required = false)String email) {
        SseEmitter sseEmitter = new SseEmitter();
        String userId = generatePublicUserEmail(email); // 각 사용자에 대한 고유 식별자 생성
        sseEmitters.put(userId, sseEmitter); // 맵에 사용자 식별자와 SSEEmitter 매핑
        sseEmitter.onCompletion(() -> sseEmitters.remove(email));
        sseEmitter.onCompletion(()-> System.out.println("completed"));// SSE 연결 종료 시 맵에서 제거
        sseEmitter.onTimeout(()-> System.out.println("time out"));

    }

    // 알림을 보내는 메서드
    public void sendNotification(String email, String notification) {

        System.out.println(sseEmitters);
        SseEmitter matcher = new SseEmitter();
        if(sseEmitters.containsKey(email)){
           try {
               matcher = sseEmitters.get(email);
               matcher.send(notification , MediaType.TEXT_PLAIN);
               matcher.complete();
               System.out.println(matcher + "에게 알림 전송 완료.");
           }catch (IOException e){
               e.printStackTrace();
           }
        }else{
            System.out.println("sse emitter 에서 등록한 사용자 중 매칭이된 사용자가 없습니다.");
        }
    }

    // 사용자 식별자 생성 메서드 (임의로 구현)
    private String generatePublicUserEmail(String email) {
        return email;
    }
}