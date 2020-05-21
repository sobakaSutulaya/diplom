package com.sobachken.learningpro.service;

import com.google.common.collect.Lists;
import com.sobachken.learningpro.common.exception.http.HttpException;
import com.sobachken.learningpro.model.task.Task;
import com.sobachken.learningpro.model.task.TaskAnswer;
import com.sobachken.learningpro.repository.TaskAnswerRepository;
import com.sobachken.learningpro.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskAnswerRepository taskAnswerRepository;

    public TaskService(TaskRepository taskRepository,
                       TaskAnswerRepository taskAnswerRepository) {
        this.taskRepository = taskRepository;
        this.taskAnswerRepository = taskAnswerRepository;
    }

    public Task getTask(UUID id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Task was not found by id = " + id));
    }

    public List<Task> getAll() {
        log.info("Get all tasks");
        return Lists.newArrayList(this.taskRepository.findAll());
    }

    public List<Task> getAllByCourse(int courseNumber) {
        log.info("Get all tasks by course = '{}'", courseNumber);
        return this.taskRepository.findAllByCourseNumber(courseNumber);
    }

    public List<Task> getAllBySubject(String subject) {
        log.info("Get all tasks by subject = '{}'", subject);
        return this.taskRepository.findAllBySubject(subject);
    }

    public UUID createTask(Task newTask, TaskAnswer taskAnswer) {
        log.info("Create new task");
        UUID taskId = UUID.randomUUID();
        newTask.setId(taskId);
        taskAnswer.setTaskId(taskId);
        taskAnswer.setId(UUID.randomUUID());
        this.validateTask(newTask, taskAnswer);
        log.info("Save task");
        this.taskRepository.save(newTask);
        log.info("Save task answer");
        this.taskAnswerRepository.save(taskAnswer);
        return taskId;
    }

    private void validateTask(Task task, TaskAnswer taskAnswer) {
        log.info("Validate task");
        if (isNull(taskAnswer.getExpectedResult())) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Task Answer can not be null");
        }
        if (isNull(task.getDescription())) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Task Description can not be null");
        }
    }

    boolean existsById(UUID id) {
        return this.taskRepository.existsById(id);
    }
}
