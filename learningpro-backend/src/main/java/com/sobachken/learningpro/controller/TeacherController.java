package com.sobachken.learningpro.controller;

import com.sobachken.learningpro.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.sobachken.learningpro.common.ApiPath.TEACHERS_V1_PATH;

@RestController
@RequestMapping(TEACHERS_V1_PATH)
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable UUID id) {
        return ResponseEntity.ok(this.teacherService.getTeacher(id));
    }
}
