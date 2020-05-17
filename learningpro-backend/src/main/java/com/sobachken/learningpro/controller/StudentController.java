package com.sobachken.learningpro.controller;

import com.sobachken.learningpro.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.sobachken.learningpro.common.ApiPath.ID_PATH;
import static com.sobachken.learningpro.common.ApiPath.STUDENTS_V1_PATH;

@RestController
@RequestMapping(STUDENTS_V1_PATH)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable UUID id) {
        return ResponseEntity.ok(this.studentService.getStudent(id));
    }
}
