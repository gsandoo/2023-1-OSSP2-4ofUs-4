package com.example.cokkiri.service;

import com.example.cokkiri.controller.SseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Component
public class NotificationService {

    private final SseController sseController;

    @Autowired
    public NotificationService(SseController sseController) {
        this.sseController = sseController;
    }

    public void sendNotificationToUser(SseEmitter emmiter, List<String> emailList, String notification) {

            for(int i = 0 ; i < emailList.size() ; i++) {
                try {
                   emmiter.send(SseEmitter.event().name("sse").data(notification));
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }


    }
}
