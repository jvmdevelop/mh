package com.jvmd.mh_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ai_messages")
public class AiMessage {
    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

}
