package com.sobachken.learningpro.model.task;

import com.sobachken.learningpro.utils.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document("completedTasks")
@AllArgsConstructor
@NoArgsConstructor
public class CompletedTask implements Identifiable {
    private UUID id;
    private UUID taskId;
    private UUID studentId;
}
