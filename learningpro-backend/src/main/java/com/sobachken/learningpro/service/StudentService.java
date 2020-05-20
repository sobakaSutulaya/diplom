package com.sobachken.learningpro.service;

import com.sobachken.learningpro.common.exception.http.HttpException;
import com.sobachken.learningpro.model.user.Student;
import com.sobachken.learningpro.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(UUID id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Student not found by id = " + id));
    }

    boolean existsById(UUID id) {
        return this.studentRepository.existsById(id);
    }

    public List<Student> getAllByGroup(String groupName) {
        return this.studentRepository.findAllByGroupName(groupName);
    }

    //todo: a very bad decision by application design
    public List<String> getAllGroupNamesByCourse(int course) {
        List<Student> courseStudents = this.studentRepository.findAllByCourseNumber(course);
        return courseStudents.stream()
                .map(Student::getGroupName)
                .distinct()
                .collect(Collectors.toList());
    }
}
