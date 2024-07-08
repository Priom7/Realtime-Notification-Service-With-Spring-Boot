package com.example.demo.controller;



import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/send-notification")
    public void sendNotification(@RequestBody String message) {
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }

    @GetMapping("/send-dummy-notifications")
    public void sendDummyNotifications() {
        List<String> dummyNotifications = Arrays.asList(
                "Notification 1: Welcome!",
                "Notification 2: You have a new message.",
                "Notification 3: Your account has been updated.",
                "Notification 4: New comment on your post.",
                "Notification 5: Friend request received."
        );

        dummyNotifications.forEach(message ->
                messagingTemplate.convertAndSend("/topic/notifications", message)
        );
    }
}
