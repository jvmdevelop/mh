package com.jvmd.mh_backend.controller;

import com.jvmd.mh_backend.model.AiMessage;
import com.jvmd.mh_backend.service.rag.RagService;
import com.jvmd.mh_backend.service.rag.RagIngestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rag")
@AllArgsConstructor
public class RagController {
    
    private final RagService ragService;
    private final RagIngestService ingestService;
    
    @PostMapping("/chat")
    public ResponseEntity<AiMessage> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            AiMessage response = ragService.handleMessage(message);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping("/ingest")
    public ResponseEntity<String> ingestDocuments(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Content cannot be empty");
        }
        
        try {
            ingestService.ingestDocument(content);
            return ResponseEntity.ok("Document ingested successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to ingest document: " + e.getMessage());
        }
    }
    
    @GetMapping("/history")
    public ResponseEntity<?> getChatHistory() {
        try {
            return ResponseEntity.ok(ingestService.getDocumentHistory());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to retrieve history: " + e.getMessage());
        }
    }
}
