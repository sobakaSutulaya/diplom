package com.sobachken.learningpro.model.migrations.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sobachken.learningpro.common.exception.migration.MongoMigrationException;
import com.sobachken.learningpro.model.user.Teacher;
import com.sobachken.learningpro.mongomigration.MongoMigration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class TeacherMigration implements MongoMigration {

    private final static String TEACHERS_PATH = "target/mongomigration/teachers/teachers.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void migrate(MongoTemplate mongoTemplate) {
        log.info("Start Teacher migration");
        try {
            List<Teacher> teachers = getTeachers();
            teachers.forEach(teacher -> this.saveTeacher(teacher, mongoTemplate));
        } catch (Exception ex) {
            log.error("Error while migrating Teachers");
            throw new MongoMigrationException(ex);
        }
        log.info("Teachers successfully migrated");
    }

    private void saveTeacher(Teacher teacher, MongoTemplate mongoTemplate) {
        Teacher savedTeacher = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("login").is(teacher.getLogin())), Teacher.class);
        if (savedTeacher == null) {
            log.info("Create Teacher with login : '{}'", teacher.getLogin());
            teacher.setId(UUID.randomUUID());
            teacher.setPassword(bCryptPasswordEncoder.encode(teacher.getPassword()));
            mongoTemplate.save(teacher);
        } else {
            log.info("Update Teacher with login : '{}'", teacher.getLogin());
            savedTeacher.setPassword(teacher.getPassword());
            mongoTemplate.save(savedTeacher);
        }
    }

    private List<Teacher> getTeachers() throws Exception {
        try {
            return objectMapper.readValue(loadTeachersFromResource().getInputStream(), new TypeReference<List<Teacher>>() {});
        } catch (Exception ex) {
            log.error("Error while loading teachers from resources");
            throw ex;
        }
    }

    private Resource loadTeachersFromResource() {
        return new ClassPathResource(TEACHERS_PATH);
    }
}
