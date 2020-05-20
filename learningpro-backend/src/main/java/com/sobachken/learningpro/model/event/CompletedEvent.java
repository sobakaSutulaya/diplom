package com.sobachken.learningpro.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document("completedEvent")
@AllArgsConstructor
@NoArgsConstructor
public class CompletedEvent {
    private UUID id;
    private UUID studentId;
    private UUID eventId;
    private List<UUID> completedTaskIds;
}
