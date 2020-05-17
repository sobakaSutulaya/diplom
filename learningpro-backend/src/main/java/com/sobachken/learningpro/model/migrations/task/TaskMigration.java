package com.sobachken.learningpro.model.migrations.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobachken.learningpro.common.exception.migration.MongoMigrationException;
import com.sobachken.learningpro.model.task.Task;
import com.sobachken.learningpro.model.task.TaskAnswer;
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
import java.util.stream.Collectors;

@Slf4j
@Component
public class TaskMigration implements MongoMigration {

    private final static String TASK_PATH = "target/mongomigration/task/tasks.json";
    private final static String TASK_ANSWERS_PATH = "target/mongomigration/task/task-answers.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void migrate(MongoTemplate mongoTemplate) {
        log.info("Start tasks migration");
        try {
            List<Task> tasks = this.getTasks();
            List<TaskAnswer> taskAnswers = this.getTaskAnswers();
            this.validateTasks(tasks, taskAnswers);
            tasks.forEach(task -> saveTask(task, mongoTemplate));
            taskAnswers.forEach(taskAnswer -> saveTaskAnswer(taskAnswer, mongoTemplate));
        } catch (Exception ex) {
            log.error("Error while migration tasks");
            throw new MongoMigrationException(ex);
        }
    }

    private void saveTaskAnswer(TaskAnswer taskAnswer, MongoTemplate mongoTemplate) {
        TaskAnswer savedTaskAnswer = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("taskId").is(taskAnswer.getTaskId())), TaskAnswer.class);
        if (savedTaskAnswer != null) {
            savedTaskAnswer.setExpectedResult(taskAnswer.getExpectedResult());
            mongoTemplate.save(savedTaskAnswer);
        } else {
            taskAnswer.setId(UUID.randomUUID());
            mongoTemplate.save(taskAnswer);
        }
    }

    private void saveTask(Task task, MongoTemplate mongoTemplate) {
        Task savedTask = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(task.getId())), Task.class);
        if (savedTask != null) {
            log.info("Update task with name = '{}'", savedTask.getName());
            savedTask.setDescription(task.getDescription());
            savedTask.setName(task.getName());
            savedTask.setSubject(task.getSubject());
            savedTask.setCourseNumber(task.getCourseNumber());
            mongoTemplate.save(savedTask);
        } else {
            log.info("Create task with id = '{}'", task.getId());
            mongoTemplate.save(task);
        }
    }

    private void validateTasks(List<Task> tasks, List<TaskAnswer> taskAnswers) {
        List<UUID> taskIds = taskAnswers.stream()
                .map(TaskAnswer::getTaskId)
                .collect(Collectors.toList());
        tasks.removeIf(task -> !taskIds.contains(task.getId()));
    }

    private List<Task> getTasks() throws Exception {
        try {
            return objectMapper.readValue(this.loadTasksFromResources().getInputStream(), new TypeReference<List<Task>>() {
            });
        } catch (Exception ex) {
            log.error("Error while load tasks from resources");
            throw ex;
        }
    }

    private List<TaskAnswer> getTaskAnswers() throws Exception {
        try {
            return objectMapper.readValue(this.loadTaskAnswersFromResources().getInputStream(), new TypeReference<List<TaskAnswer>>() {
            });
        } catch (Exception ex) {
            log.error("Error while load tasks from resources");
            throw ex;
        }
    }

    private Resource loadTasksFromResources() {
        return new ClassPathResource(TASK_PATH);
    }

    private Resource loadTaskAnswersFromResources() {
        return new ClassPathResource(TASK_ANSWERS_PATH);
    }
}
