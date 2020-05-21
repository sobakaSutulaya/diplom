package com.sobachken.learningpro.service;

import com.sobachken.learningpro.common.exception.http.HttpException;
import com.sobachken.learningpro.model.task.CompletedTask;
import com.sobachken.learningpro.repository.CompletedTaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CompletedTaskService {

    private final CompletedTaskRepository completedTaskRepository;
    private final TaskService taskService;
    private final StudentService studentService;

    public CompletedTaskService(CompletedTaskRepository completedTaskRepository,
                                TaskService taskService,
                                StudentService studentService) {
        this.completedTaskRepository = completedTaskRepository;
        this.taskService = taskService;
        this.studentService = studentService;
    }

    public CompletedTask getCompletedTask(UUID id) {
        log.info("Get completed task by id = '{}'", id);
        return this.completedTaskRepository.findById(id)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Completed Task not found by id = " + id));
    }

    public UUID saveCompletedTask(UUID taskId, UUID studentId) {
        log.info("save completed task for user id = '{}' and task id = '{}'", taskId, studentId);
        if (!this.studentService.existsById(studentId)) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Student not found my id = " + studentId);
        }

        if (!this.taskService.existsById(taskId)) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Task not found by id = " + taskId);
        }

        Optional<CompletedTask> completedTaskOptional = this.completedTaskRepository.findByStudentIdAndTaskId(studentId, taskId);
        if (completedTaskOptional.isPresent()) {
            return completedTaskOptional.get().getId();
        } else {
            CompletedTask completedTask = new CompletedTask();
            completedTask.setId(UUID.randomUUID());
            completedTask.setStudentId(studentId);
            completedTask.setTaskId(taskId);
            return completedTaskRepository.save(completedTask).getId();
        }
    }

    public List<CompletedTask> getAllByStudentId(UUID studentId) {
        log.info("Get completed tasks by student id = '{}'", studentId);
        return this.completedTaskRepository.findAllByStudentId(studentId);
    }
}
