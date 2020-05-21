package com.sobachken.learningpro.model.event;

import com.sobachken.learningpro.mongomigration.annotation.ChangeLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@ChangeLog(version = 5)
@Document("events")
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private UUID id;
    private String name;
    private String description;
    private List<UUID> taskIds;
    private Calendar startDate;
    private Calendar endDate;
    private String subject;
    private int courseNumber;
    private UUID teacherId;
    private List<String> groupNames;
}
