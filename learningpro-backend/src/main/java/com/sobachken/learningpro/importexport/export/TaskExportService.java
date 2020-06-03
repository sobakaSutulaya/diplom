package com.sobachken.learningpro.importexport.export;

import com.sobachken.learningpro.model.task.Task;
import com.sobachken.learningpro.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskExportService implements Exportable<Task>{

    private final TaskRepository taskRepository;

    public TaskExportService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Collection<Task> exportData() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void importData(Collection<Task> data) {
        this.taskRepository.saveAll(data);
    }

    @Override
    public Class<Task> entityClass() {
        return Task.class;
    }
}
