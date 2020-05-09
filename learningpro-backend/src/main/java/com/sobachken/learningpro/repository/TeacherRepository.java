package com.sobachken.learningpro.repository;

import com.sobachken.learningpro.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, UUID> {

    Optional<Teacher> findByLogin(String login);
}
