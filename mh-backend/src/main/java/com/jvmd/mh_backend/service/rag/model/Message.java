package com.jvmd.mh_backend.service.rag.model;

public record Message(
        String role,
        String content
) {
}