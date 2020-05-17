package com.sobachken.learningpro.repository;

import com.sobachken.learningpro.model.task.TaskAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskAnswerRepository extends CrudRepository<TaskAnswer, UUID> {
    Optional<TaskAnswer> findByTaskId(UUID taskId);
}
