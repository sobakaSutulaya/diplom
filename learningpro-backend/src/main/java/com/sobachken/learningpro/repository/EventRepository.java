package com.sobachken.learningpro.repository;

import com.sobachken.learningpro.model.event.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {

    List<Event> findAllByTeacherId(UUID teacherId);
    List<Event> findAllBySubject(String subject);
}
