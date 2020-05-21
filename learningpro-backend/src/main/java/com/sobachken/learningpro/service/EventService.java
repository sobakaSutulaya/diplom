package com.sobachken.learningpro.service;

import com.google.common.collect.Lists;
import com.sobachken.learningpro.common.exception.http.HttpException;
import com.sobachken.learningpro.model.event.Event;
import com.sobachken.learningpro.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final TaskService taskService;

    public EventService(EventRepository eventRepository, TaskService taskService) {
        this.eventRepository = eventRepository;
        this.taskService = taskService;
    }

    public List<Event> getAll() {
        log.info("Get all events");
        return Lists.newArrayList(this.eventRepository.findAll());
    }

    public Event getEvent(UUID id) {
        log.info("Get event by id = '{}'", id);
        return this.eventRepository.findById(id)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Event not found by id = " + id));
    }

    public List<Event> getAllByTeacherId(UUID teacherId) {
        log.info("Get all events by teacher id = '{}'", teacherId);
        return this.eventRepository.findAllByTeacherId(teacherId);
    }

    public List<Event> getAllBySubject(String subject) {
        log.info("Get all events by subject = '{}'", subject);
        return this.eventRepository.findAllBySubject(subject);
    }

    public List<Event> getAllByGroup(String groupName) {
        log.info("get all events by groupName = '{}'", groupName);
        return StreamSupport.stream(this.eventRepository.findAll().spliterator(), false)
                .filter(event -> event.getGroupNames().contains(groupName))
                .collect(Collectors.toList());
    }

    public UUID createEvent(Event event) {
        if (isNull(event.getId())) {
            event.setId(UUID.randomUUID());
        }
        //todo: think about groups
        if (isNull(event.getGroupNames())) {
            log.warn("Event will shown for all groups");
        }

        if (nonNull(event.getTaskIds())) {
            this.validateTaskIds(event.getTaskIds());
        }

        return this.eventRepository.save(event).getId();
    }

    private void validateTaskIds(List<UUID> taskIds) {
        for (UUID taskId : taskIds) {
            if (!taskService.existsById(taskId)) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Task with id = " + taskId + " is not exist");
            }
        }
    }

    public void updateEvent(Event event) {
        Event savedEvent = this.eventRepository.findById(event.getId())
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Event not exist by id = " + event.getId()));

        this.mergeEvents(savedEvent, event);
        this.validateTaskIds(savedEvent.getTaskIds());
        this.eventRepository.save(savedEvent);
    }

    private void mergeEvents(Event savedEvent, Event updatedEvent) {
        if (nonNull(updatedEvent.getTaskIds())) {
            savedEvent.setTaskIds(updatedEvent.getTaskIds());
        }

        if (nonNull(updatedEvent.getGroupNames())) {
            savedEvent.setGroupNames(updatedEvent.getGroupNames());
        }

        if (nonNull(updatedEvent.getEndDate())) {
            savedEvent.setEndDate(updatedEvent.getEndDate());
        }

        if (nonNull(updatedEvent.getStartDate())) {
            savedEvent.setStartDate(updatedEvent.getStartDate());
        }

        if (nonNull(updatedEvent.getSubject())) {
            savedEvent.setSubject(updatedEvent.getSubject());
        }
    }
}
