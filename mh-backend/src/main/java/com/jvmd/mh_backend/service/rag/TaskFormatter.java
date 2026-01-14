package com.jvmd.mh_backend.service.rag;


import com.jvmd.mh_backend.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskFormatter {
    private final PromtBuilder builder;
    private final AiClient client;

    public List<Task> format(String message) {
        String chat = client.chat(builder.buildForTasks(message));
        List<Task> tasks = new ArrayList<>();
        Arrays.stream(chat.split(";")).forEach(task -> {
            String[] split = task.split(":");
            tasks.add(Task.builder().name(split[0]).context(split[1]).build());
        });

        return tasks;
    }

}
