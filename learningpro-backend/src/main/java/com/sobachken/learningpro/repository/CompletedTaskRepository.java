package com.sobachken.learningpro.repository;

import com.sobachken.learningpro.model.task.CompletedTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompletedTaskRepository extends CrudRepository<CompletedTask, UUID> {

    Optional<CompletedTask> findByStudentIdAndTaskId(UUID studentId, UUID taskId);
    List<CompletedTask> findAllByStudentId(UUID studentId);
}
