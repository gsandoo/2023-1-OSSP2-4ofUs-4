package com.example.cokkiri.service;

import com.example.cokkiri.controller.SseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationService {

    private final SseController sseController;

    @Autowired
    public NotificationService(SseController sseController) {
        this.sseController = sseController;
    }

    public void sendNotificationToUser(List<String> emailList, String notification) {
        for(int i = 0 ; i < emailList.size() ; i++){
            sseController.sendNotification(emailList.get(i), notification);
        }

    }
}
