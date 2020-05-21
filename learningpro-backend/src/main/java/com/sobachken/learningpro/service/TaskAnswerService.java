package com.sobachken.learningpro.service;

import com.sobachken.learningpro.common.exception.http.HttpException;
import com.sobachken.learningpro.model.task.TaskAnswer;
import com.sobachken.learningpro.repository.TaskAnswerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class TaskAnswerService {

    private final TaskAnswerRepository taskAnswerRepository;
    private final TaskService taskService;

    public TaskAnswerService(TaskAnswerRepository taskAnswerRepository,
                             TaskService taskService) {
        this.taskAnswerRepository = taskAnswerRepository;
        this.taskService = taskService;
    }

    public TaskAnswer getTaskAnswer(UUID id) {
        log.info("Get task answer by id = '{}'", id);
        return this.taskAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Task Answer not found by id = " + id));
    }

    public TaskAnswer getTaskAnswerByTaskId(UUID taskId) {
        log.info("Get task answer by task id = '{}'", taskId);
        if (!taskService.existsById(taskId)) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Task not exist by id = " + taskId);
        }

        return this.taskAnswerRepository.findByTaskId(taskId)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Task Answer not found by task id = " + taskId));
    }
}
