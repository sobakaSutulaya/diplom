package com.sobachken.learningpro.mongomigration;

import com.sobachken.learningpro.common.exception.migration.MongoMigrationException;
import com.sobachken.learningpro.mongomigration.annotation.ChangeLogAnnotationProcessor;
import com.sobachken.learningpro.mongomigration.model.MongoHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class MongoMigrationProcessor {
    private final static String MONGO_HISTORY_ID = "9b6dbe11-f978-4305-9254-e823c42c7779";

    private final MongoTemplate mongoTemplate;
    private final ChangeLogAnnotationProcessor changeLogProcessor;
    private final List<MongoMigration> migrations;

    public MongoMigrationProcessor(MongoTemplate mongoTemplate,
                                   ChangeLogAnnotationProcessor changeLogProcessor,
                                   List<MongoMigration> migrations) {
        this.mongoTemplate = mongoTemplate;
        this.changeLogProcessor = changeLogProcessor;
        this.migrations = migrations;
    }

    @PostConstruct
    void executeMigration() {
        log.info("Start mongo migration");
        MongoHistory mongoHistory = getMongoHistory();
        log.info("Current mongo history information : '{}'", mongoHistory);
        if (isMigrationNeeded(mongoHistory)) {
            migrate(mongoHistory);
        } else {
            log.info("Mongo History data is actual, no migration needed");
        }

    }

    private boolean isMigrationNeeded(MongoHistory mongoHistory) {
        final Long changeLogVersion = this.changeLogProcessor.getActualDBVersion();

        if (changeLogVersion > mongoHistory.getDbVersion()) {
            log.info("Actual db version is '{}'", changeLogVersion);
            mongoHistory.setDbVersion(changeLogVersion);
            return true;
        } else {
            return !mongoHistory.isMigrated();
        }
    }

    private void migrate(MongoHistory mongoHistory) {
        if (migrations.isEmpty()) {
            mongoHistory.setMigrated(false);
            log.warn("No migrations were found");
            mongoTemplate.save(mongoHistory);
            return;
        }
        try {
            migrations.forEach(migration -> migration.migrate(mongoTemplate));
            mongoHistory.setMigrated(true);
        } catch (MongoMigrationException ex) {
            log.error("Migration failed cause :", ex);
            mongoHistory.setMigrated(false);
        }
        mongoHistory.setLastUpdateDate(LocalDateTime.now());
        mongoTemplate.save(mongoHistory);
    }

    private MongoHistory getMongoHistory() {
        MongoHistory mongoHistory = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(UUID.fromString(MONGO_HISTORY_ID))), MongoHistory.class);

        if (isNull(mongoHistory)) {
            mongoHistory = new MongoHistory();
            mongoHistory.setId(UUID.fromString(MONGO_HISTORY_ID));
            mongoHistory.setDbVersion(0L);
            mongoTemplate.save(mongoHistory);
        }

        return mongoHistory;
    }

}
