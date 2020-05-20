package com.sobachken.learningpro.controller;

import com.sobachken.learningpro.model.event.CompletedEvent;
import com.sobachken.learningpro.service.CompletedEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sobachken.learningpro.common.ApiPath.COMPLETED_EVENT_V1_PATH;
import static com.sobachken.learningpro.common.ApiPath.MARK_AS_COMPLETE_PATH;

@RestController
@RequestMapping(COMPLETED_EVENT_V1_PATH)
public class CompletedEventController {

    private final CompletedEventService completedEventService;

    public CompletedEventController(CompletedEventService completedEventService) {
        this.completedEventService = completedEventService;
    }

    @PostMapping
    public ResponseEntity<?> markAsComplete(@RequestBody CompletedEvent completedEvent) {
        return ResponseEntity.ok(this.completedEventService.createCompletedEvent(completedEvent));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getCompletedEvents(@PathVariable UUID studentId) {
        return ResponseEntity.ok(this.completedEventService.getCompletedEventsByStudentId(studentId));
    }
}
