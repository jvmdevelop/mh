package com.jvmd.mh_backend.service.rag;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class PromtBuilder {

    private enum EPromts {
        USER_RULE(""),
        MATH_RULE(""),
        WRITE_RULE("");

        private final String content;

        EPromts(String content) {
            this.content = content;
        }
    }

    public String build(String question) {
        StringBuilder builder = new StringBuilder();

        builder.append("USER QUESTION: ").append(question).append("\n");
        Arrays.stream(EPromts.values()).forEach((ePromt) -> {
            builder.append(ePromt.name()).append(" ").append(ePromt.content);
        });

        return builder.toString();
    }

}
