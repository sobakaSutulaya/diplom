package com.sobachken.learningpro.mongomigration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobachken.learningpro.model.event.Event;
import com.sobachken.learningpro.model.task.TaskAnswer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class MongoMigrationProcessorTest {


    @Test
    public void test() {
        System.out.println(UUID.randomUUID());
    }

    @Test
    public void secondTest() throws Exception {
        Event event = new Event();
        event.setId(UUID.randomUUID());
        event.setGroupNames(Collections.singletonList("PO-63"));
        event.setTeacherId(UUID.randomUUID());
        event.setSubject("programming essentials");
        event.setTaskIds(Collections.singletonList(UUID.randomUUID()));
        Calendar start = new GregorianCalendar(2020, Calendar.JUNE, 21, 14, 0);
        Calendar end = new GregorianCalendar(2020, Calendar.JUNE, 21, 18, 0);
        event.setStartDate(start);
        event.setEndDate(end);
        event.setName("programming essentials exam");
        event.setDescription("programming essentials exam for first course");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(event);
        System.out.println(json);
    }
}
