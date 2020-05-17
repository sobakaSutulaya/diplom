package com.sobachken.learningpro.mongomigration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobachken.learningpro.model.task.TaskAnswer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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
        TaskAnswer task = new TaskAnswer();
        List<TaskAnswer.InputToOutputArgument> args = new ArrayList<>();
        args.add(new TaskAnswer.InputToOutputArgument("2, 2", "4"));
        task.setExpectedResult(args);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(task);
        System.out.println(json);
    }
}
