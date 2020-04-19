package com.sobachken.learningpro.importexport.export;

import com.sobachken.learningpro.model.Teacher;
import com.sobachken.learningpro.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeacherExportService implements Exportable<Teacher> {

    private final TeacherRepository teacherRepository;

    public TeacherExportService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Collection<Teacher> exportData() {
        return StreamSupport.stream(teacherRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void importData(Collection<Teacher> data) {
        teacherRepository.saveAll(data);
    }

    @Override
    public Class<Teacher> entityClass() {
        return Teacher.class;
    }
}
