package com.jvmd.mh_backend.service.rag;

import com.jvmd.mh_backend.model.AiMessage;
import com.jvmd.mh_backend.model.UserMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RagService {
    private final PromtBuilder builder;
    private final RetrivialSearcher searcher;
    private final AiClient client;


    public AiMessage handleMessage(String message) {
        List<String> list = searcher.retrieve(message);
        list.add(0, builder.build(message));




        return AiMessage.builder()
                .id(UUID.randomUUID())
                .content(client.chat(list.stream().reduce("", (a, b) -> a + b)))
                .build();
    }



}
