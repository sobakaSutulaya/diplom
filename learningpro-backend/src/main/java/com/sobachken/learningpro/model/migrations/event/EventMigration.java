package com.sobachken.learningpro.model.migrations.event;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobachken.learningpro.common.exception.migration.MongoMigrationException;
import com.sobachken.learningpro.model.event.Event;
import com.sobachken.learningpro.mongomigration.MongoMigration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class EventMigration implements MongoMigration {

    private static final String EVENTS_PATH = "/target/mongomigration/events/events.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void migrate(MongoTemplate mongoTemplate) {
        log.info("Start event migration");
        try {
            List<Event> events = this.getEvents();
            events.forEach(event -> this.createOrUpdateEvent(event, mongoTemplate));
        } catch (Exception ex) {
            log.error("Error while events migration");
            throw new MongoMigrationException(ex);
        }
    }

    private void createOrUpdateEvent(Event event, MongoTemplate mongoTemplate) {
        Event savedEvent = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(event.getId())), Event.class);
        if (savedEvent != null) {
            savedEvent.setSubject(event.getSubject());
            savedEvent.setName(event.getName());
            savedEvent.setStartDate(event.getStartDate());
            savedEvent.setEndDate(event.getEndDate());
            savedEvent.setTaskIds(event.getTaskIds());
            savedEvent.setTeacherId(event.getTeacherId());
            savedEvent.setGroupNames(event.getGroupNames());
            mongoTemplate.save(savedEvent);
        } else {
            mongoTemplate.save(event);
        }
    }

    private List<Event> getEvents() throws Exception {
        try {
            return objectMapper.readValue(this.loadEvents().getInputStream(), new TypeReference<List<Event>>(){});
        } catch (Exception ex) {
            log.error("Error while load events from resources");
            throw ex;
        }
    }

    private Resource loadEvents() {
        return new ClassPathResource(EVENTS_PATH);
    }
}
