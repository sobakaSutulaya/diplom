package com.sobachken.learningpro.model.task;

import com.sobachken.learningpro.mongomigration.annotation.ChangeLog;
import com.sobachken.learningpro.utils.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document("tasks")
@ChangeLog(version = 2)
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Identifiable {
    private UUID id;
    private String name;
    private String description;
    private int courseNumber;
    private String subject;
}
