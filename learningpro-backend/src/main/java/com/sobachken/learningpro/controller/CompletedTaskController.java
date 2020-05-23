package com.sobachken.learningpro.controller;

import com.sobachken.learningpro.service.CompletedTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sobachken.learningpro.common.ApiPath.*;

@RestController
@RequestMapping(COMPLETED_TASK_V1_PATH)
public class CompletedTaskController {

    private final CompletedTaskService completedTaskService;

    public CompletedTaskController(CompletedTaskService completedTaskService) {
        this.completedTaskService = completedTaskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompletedTask(@PathVariable UUID id) {
        return ResponseEntity.ok(this.completedTaskService.getCompletedTask(id));
    }

    @GetMapping("/{studentId}" + ALL_PATH)
    public ResponseEntity<?> getAllByStudentId(@PathVariable UUID studentId) {
        return ResponseEntity.ok(this.completedTaskService.getAllByStudentId(studentId));
    }

    @PostMapping(MARK_AS_COMPLETE_PATH + "/{studentId}/{taskId}")
    public ResponseEntity<?> markAsComplete(@PathVariable UUID studentId, @PathVariable UUID taskId) {
        return ResponseEntity.ok(this.completedTaskService.saveCompletedTask(taskId, studentId));
    }

    @GetMapping(SOLUTION_PATH + "/{studentId}/{taskId}")
    public ResponseEntity<?> getSolution(@PathVariable UUID studentId, @PathVariable UUID taskId) {
        return ResponseEntity.ok(this.completedTaskService.getSolution(studentId, taskId));
    }
}
