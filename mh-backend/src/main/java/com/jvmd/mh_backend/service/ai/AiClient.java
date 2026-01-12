package com.jvmd.mh_backend.service.ai;

import com.jvmd.mh_backend.service.ai.model.ChatRequest;
import com.jvmd.mh_backend.service.ai.model.ChatResponse;
import com.jvmd.mh_backend.service.ai.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
public class AiClient {

    private final RestClient restClient;
    private final String model;

    public AiClient(
            @Value("${llm7.api.base-url:https://api.llm7.io/v1}") String baseUrl,
            @Value("${llm7.api.key:unused}") String apiKey,
            @Value("${llm7.api.model:bidara}") String model
    ) {
        this.model = model;
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        log.info("Initialized LLM7 client with base URL: {} and model: {}", baseUrl, model);
    }

    public String chat(String userMessage) {
        ChatRequest request = new ChatRequest(
                model,
                List.of(new Message("user", userMessage)),
                0.7,
                500
        );

        log.debug("Sending chat request to LLM7.io: {}", userMessage);

        try {
            ChatResponse response = restClient.post()
                    .uri("/chat/completions")
                    .body(request)
                    .retrieve()
                    .body(ChatResponse.class);

            if (response != null && response.choices() != null && !response.choices().isEmpty()) {
                String content = response.choices().get(0).message().content();
                log.debug("Received response from LLM7.io: {} chars", content.length());
                return content;
            }

            return "Ответ не получен";

        } catch (Exception e) {
            log.error("Error calling LLM7.io API: {}", e.getMessage(), e);
            return "Извините, произошла ошибка при обращении к AI модели.";
        }
    }
}