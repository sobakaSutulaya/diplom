package com.sobachken.learningpro.importexport.export;

import com.sobachken.learningpro.model.task.TaskAnswer;
import com.sobachken.learningpro.repository.TaskAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskAnswerExportService implements Exportable<TaskAnswer>{

    private final TaskAnswerRepository taskAnswerRepository;

    public TaskAnswerExportService(TaskAnswerRepository taskAnswerRepository) {
        this.taskAnswerRepository = taskAnswerRepository;
    }

    @Override
    public Collection<TaskAnswer> exportData() {
        return StreamSupport.stream(this.taskAnswerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void importData(Collection<TaskAnswer> data) {
        this.taskAnswerRepository.saveAll(data);
    }

    @Override
    public Class<TaskAnswer> entityClass() {
        return TaskAnswer.class;
    }
}
