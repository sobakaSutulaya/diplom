package com.sobachken.learningpro.mongomigration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoHistory {
    private UUID id;
    private Long dbVersion;
    private LocalDateTime lastUpdateDate;
    private boolean isMigrated;
}
