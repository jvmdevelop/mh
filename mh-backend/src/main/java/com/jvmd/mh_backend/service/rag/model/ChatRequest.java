package com.jvmd.mh_backend.service.rag.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatRequest(
        String model,
        List<Message> messages,
        double temperature,
        @JsonProperty("max_tokens") int maxTokens
) {
}