package com.jvmd.mh_backend.controller;

import com.jvmd.mh_backend.model.AiMessage;
import com.jvmd.mh_backend.service.rag.RagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ChatController {
    private final RagService ragService;

    public ChatController(RagService ragService) {
        this.ragService = ragService;
    }

    @MessageMapping("/api/public/chat")
    @SendTo("/topic/messages")
    public ResponseEntity<AiMessage> handleMessage(String message) {
        AiMessage handledMessage = null;

        try {
            handledMessage = ragService.handleMessage(message);
        } catch (Exception ex)
        {
           log.error(ex.getMessage());
           return ResponseEntity.badRequest().body(handledMessage);
        }
        return ResponseEntity.ok(handledMessage);
    }
}
