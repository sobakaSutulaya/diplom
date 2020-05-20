package com.sobachken.learningpro.repository;

import com.sobachken.learningpro.model.event.CompletedEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompletedEventRepository extends CrudRepository<CompletedEvent, UUID> {

    List<CompletedEvent> findAllByStudentId(UUID studentId);
    Optional<CompletedEvent> findByEventId(UUID eventId);
    Optional<CompletedEvent> findByStudentIdAndEventId(UUID studentId, UUID eventId);
}
