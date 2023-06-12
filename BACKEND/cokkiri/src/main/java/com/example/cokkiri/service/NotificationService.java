package com.example.cokkiri.service;

import com.example.cokkiri.model.Notification;
import com.example.cokkiri.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public SseEmitter subscribe(String userId, String lastEventId) {
        // 1
        String id = userId + "_" + System.currentTimeMillis();

        // 2
        SseEmitter emitter = notificationRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));

        emitter.onCompletion(() -> notificationRepository.deleteById(id));
        emitter.onTimeout(() -> notificationRepository.deleteById(id));

        // 3
        // 503 에러를 방지하기 위한 더미 이벤트 전송
        sendToClient(emitter, id, "EventStream Created. [userId=" + userId + "]");

        // 4
        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = notificationRepository.findAllEventCacheStartWithByEmail(String.valueOf(userId));
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }

    // 3
    private void sendToClient(SseEmitter emitter, String id, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data));
        } catch (IOException exception) {
            notificationRepository.deleteById(id);
            throw new RuntimeException("연결 오류!");
        }
    }


    public void send(List<String> receiverList, String content, String type) {
        List<Notification> notifications = new ArrayList<>();

        Map<String, SseEmitter> sseEmitters;

        for (int i = 0; i < receiverList.size(); i++) {

            int finalI = i;

            sseEmitters = new HashMap<>();

            notifications.add(createNotification(receiverList.get(i).toString(), content, type));

            sseEmitters.putAll(notificationRepository.findAllEmitterStartWithByEmail(receiverList.get(i).toString()));

            sseEmitters.forEach(
                    (key, emitter) -> {
                        // 데이터 캐시 저장(유실된 데이터 처리하기 위함)
                        notificationRepository.saveEventCache(key, notifications.get(finalI));
                        // 데이터 전송
                        sendToClient(emitter, key, notifications.get(finalI));
                    }
            );
        }
    }

    private Notification createNotification(String receiver, String content, String type) {
        if (type.equals("free")) {
            return Notification.builder()
                    .receiver(receiver)     // 사용자 이메일
                    .content(content)       // "매칭이 성사되었습니다"
                    .notificationType(type) // free
                    .isRead(false)          // 사용자가 읽었는지 판단
                    .build();
        } else if (type.equals("class")) {
            return Notification.builder()
                    .receiver(receiver)     // 사용자 이메일
                    .content(content)
                    .notificationType(type)
                    .isRead(false)
                    .build();
        }
        else {
            return null;
        }
    }


}
