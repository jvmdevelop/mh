package com.jvmd.mh_backend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/api/public/chat")
    @SendTo("/topic/messages")
    public String handleMessage(String message) {
        return "Serve вововлдов: " + message;
    }
}
