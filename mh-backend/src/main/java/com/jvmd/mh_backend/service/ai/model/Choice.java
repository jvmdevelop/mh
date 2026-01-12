package com.jvmd.mh_backend.service.ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice(
        int index,
        Message message,
        @JsonProperty("finish_reason") String finishReason
) {
}
