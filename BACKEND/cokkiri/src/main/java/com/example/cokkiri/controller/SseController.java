package com.example.cokkiri.controller;

import com.example.cokkiri.service.SseService;
import org.springframework.stereotype.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class SseController {

    private Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    private  final SseService sseService;

    public SseController(SseService sseService) {
        this.sseService = sseService;
    }

    // sse 연결
    @GetMapping(value = "/subscribe/{email}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable String email, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId){
        return sseService.subscribe(email, lastEventId);
    }

    // 사용자 식별자 생성 메서드 (임의로 구현)
    private String generatePublicUserEmail(String email) {
        return email;
    }
}