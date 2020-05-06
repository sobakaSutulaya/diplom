package com.sobachken.learningpro.mongomigration;

import org.springframework.data.mongodb.core.MongoTemplate;

public interface MongoMigration {
    void migrate(MongoTemplate mongoTemplate);
}
