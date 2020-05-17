package com.sobachken.learningpro.controller;

import com.sobachken.learningpro.model.task.dto.CreateTaskRequest;
import com.sobachken.learningpro.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sobachken.learningpro.common.ApiPath.*;

@RestController
@RequestMapping(TASKS_V1_PATH)
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.taskService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable UUID id) {
        return ResponseEntity.ok(this.taskService.getTask(id));
    }

    @GetMapping("/{subject}" + ALL_PATH)
    public ResponseEntity<?> getAllBySubject(@PathVariable String subject) {
        return ResponseEntity.ok(this.taskService.getAllBySubject(subject));
    }

    @GetMapping("/{course}" + ALL_PATH)
    public ResponseEntity<?> getAllByCourseNumber(@PathVariable int course) {
        return ResponseEntity.ok(this.taskService.getAllByCourse(course));
    }

    @Secured("ADMIN_ROLE")
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        return ResponseEntity.ok(this.taskService.createTask(createTaskRequest.getTask(), createTaskRequest.getTaskAnswer()));
    }


}
