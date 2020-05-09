package com.sobachken.learningpro.importexport.export;

import com.sobachken.learningpro.model.Student;
import com.sobachken.learningpro.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentExportService implements Exportable<Student>{

    private final StudentRepository studentRepository;

    public StudentExportService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Collection<Student> exportData() {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void importData(Collection<Student> data) {
        studentRepository.saveAll(data);
    }

    @Override
    public Class<Student> entityClass() {
        return Student.class;
    }
}
