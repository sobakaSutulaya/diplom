package com.sobachken.learningpro.service;

import com.sobachken.learningpro.common.exception.http.HttpException;
import com.sobachken.learningpro.model.user.Teacher;
import com.sobachken.learningpro.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher getTeacher(UUID id) {
        return this.teacherRepository.findById(id)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Teacher not found by id = " + id));
    }
}
