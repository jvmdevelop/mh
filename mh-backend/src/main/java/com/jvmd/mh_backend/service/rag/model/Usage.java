package com.jvmd.mh_backend.service.rag.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Usage(
        @JsonProperty("prompt_tokens") int promptTokens,
        @JsonProperty("completion_tokens") int completionTokens,
        @JsonProperty("total_tokens") int totalTokens
) {
}