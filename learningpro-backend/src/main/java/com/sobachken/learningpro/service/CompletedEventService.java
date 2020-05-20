package com.sobachken.learningpro.service;

import com.sobachken.learningpro.common.exception.http.HttpException;
import com.sobachken.learningpro.model.event.CompletedEvent;
import com.sobachken.learningpro.repository.CompletedEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CompletedEventService {

    private final CompletedEventRepository completedEventRepository;

    public CompletedEventService(CompletedEventRepository completedEventRepository) {
        this.completedEventRepository = completedEventRepository;
    }

    public List<CompletedEvent> getCompletedEventsByStudentId(UUID studentId) {
        return this.completedEventRepository.findAllByStudentId(studentId);
    }

    public UUID createCompletedEvent(CompletedEvent completedEvent) {
        Optional<CompletedEvent> savedEventOptional = this.completedEventRepository.findByStudentIdAndEventId(completedEvent.getStudentId(), completedEvent.getEventId());
        if (savedEventOptional.isPresent()) {
            CompletedEvent savedEvent = savedEventOptional.get();
            savedEvent.setCompletedTaskIds(completedEvent.getCompletedTaskIds());
            return this.completedEventRepository.save(savedEvent).getId();
        }
        return this.completedEventRepository.save(completedEvent).getId();
    }

}
