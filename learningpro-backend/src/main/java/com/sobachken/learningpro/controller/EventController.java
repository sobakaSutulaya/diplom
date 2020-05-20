package com.sobachken.learningpro.controller;

import com.sobachken.learningpro.model.event.Event;
import com.sobachken.learningpro.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sobachken.learningpro.common.ApiPath.*;

@RestController
@RequestMapping(EVENTS_V1_PATH)
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents() {
        return ResponseEntity.ok(this.eventService.getAll());
    }

    @GetMapping(BY_SUBJECT_PATH + "/{subject}")
    public ResponseEntity<?> getAllBySubject(@PathVariable String subject) {
        return ResponseEntity.ok(this.eventService.getAllBySubject(subject));
    }

    @Secured("ADMIN_ROLE")
    @GetMapping(BY_TEACHER_PATH + "/{teacherId}")
    public ResponseEntity<?> getAllByTeacherId(@PathVariable UUID teacherId) {
        return ResponseEntity.ok(this.eventService.getAllByTeacherId(teacherId));
    }

    @GetMapping(BY_GROUP_PATH + "/{group}")
    public ResponseEntity<?> getAllByGroup(@PathVariable String group) {
        return ResponseEntity.ok(this.eventService.getAllByGroup(group));
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event newEvent) {
        return ResponseEntity.ok(this.eventService.createEvent(newEvent));
    }
}
