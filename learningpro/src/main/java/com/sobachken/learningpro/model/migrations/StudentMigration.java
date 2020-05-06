package com.sobachken.learningpro.model.migrations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobachken.learningpro.common.exception.migration.MongoMigrationException;
import com.sobachken.learningpro.model.Student;
import com.sobachken.learningpro.mongomigration.MongoMigration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class StudentMigration implements MongoMigration {

    private final static String STUDENTS_PATH = "mongomigration/students/students.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void migrate(MongoTemplate mongoTemplate) throws MongoMigrationException {
        log.info("Start Student migration");
        try {
            List<Student> students = getStudents();
            students.forEach(student -> this.saveStudent(student, mongoTemplate));
        } catch (Exception ex) {
            log.error("Error while migrating students");
            throw new MongoMigrationException(ex);
        }
        log.info("Students successfully migrated");
    }

    private void saveStudent(Student student, MongoTemplate mongoTemplate) {
        Student savedStudent = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("cardNumber").is(student.getCardNumber())), Student.class);
        if (savedStudent == null) {
            log.info("Create student with cardNumber : '{}'", student.getCardNumber());
            student.setId(UUID.randomUUID());
            mongoTemplate.save(student);
        } else {
            log.info("Update student with cardNumber : '{}'", savedStudent.getCardNumber());
            mergeStudent(savedStudent, student);
            mongoTemplate.save(savedStudent);
        }
    }

    private void mergeStudent(Student savedStudent, Student updatedStudent) {
        if (updatedStudent.getFirstName() != null) {
            savedStudent.setFirstName(updatedStudent.getFirstName());
        }
        if (updatedStudent.getLastName() != null) {
            savedStudent.setLastName(updatedStudent.getLastName());
        }

        if (updatedStudent.getGroupName() != null) {
            savedStudent.setGroupName(updatedStudent.getGroupName());
        }
    }

    private List<Student> getStudents() {
        try {
            return objectMapper.readValue(loadStudentsFromResources().getInputStream(), new TypeReference<List<Student>>() {});
        } catch (Exception ex) {
            log.error("Error during load students, cause : '{}'", ex);
        }
        return Collections.emptyList();
    }

    private Resource loadStudentsFromResources() {
        return new ClassPathResource(STUDENTS_PATH);
    }
}
