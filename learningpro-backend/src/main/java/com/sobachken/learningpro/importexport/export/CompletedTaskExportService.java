package com.sobachken.learningpro.importexport.export;

import com.sobachken.learningpro.model.task.CompletedTask;
import com.sobachken.learningpro.repository.CompletedTaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompletedTaskExportService implements Exportable<CompletedTask> {

    private final CompletedTaskRepository completedTaskRepository;

    public CompletedTaskExportService(CompletedTaskRepository completedTaskRepository) {
        this.completedTaskRepository = completedTaskRepository;
    }

    @Override
    public Collection<CompletedTask> exportData() {
        return StreamSupport.stream(completedTaskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void importData(Collection<CompletedTask> data) {
        completedTaskRepository.saveAll(data);
    }

    @Override
    public Class<CompletedTask> entityClass() {
        return CompletedTask.class;
    }
}
