package com.sobachken.learningpro.repository;

import com.sobachken.learningpro.model.task.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {

    List<Task> findAllByCourseNumber(int courseNumber);
    List<Task> findAllBySubject(String subject);
}
