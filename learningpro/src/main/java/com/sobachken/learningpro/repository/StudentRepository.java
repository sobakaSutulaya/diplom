package com.sobachken.learningpro.repository;

import com.sobachken.learningpro.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<Student, UUID> {

    Optional<Student> findByCardNumber(String cardNumber);
}
