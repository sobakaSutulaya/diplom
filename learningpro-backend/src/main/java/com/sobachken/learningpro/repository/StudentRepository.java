package com.sobachken.learningpro.repository;

import com.sobachken.learningpro.model.user.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<Student, UUID> {

    Optional<Student> findByCardNumber(String cardNumber);
    List<Student> findAllByGroupName(String groupName);
    List<Student> findAllByCourseNumber(int courseNumber);
}
