package com.sobachken.learningpro.importexport.export;

import com.sobachken.learningpro.model.event.CompletedEvent;
import com.sobachken.learningpro.repository.CompletedEventRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompletedEventExportService implements Exportable<CompletedEvent> {

    private CompletedEventRepository completedEventRepository;

    public CompletedEventExportService(CompletedEventRepository completedEventRepository) {
        this.completedEventRepository = completedEventRepository;
    }

    @Override
    public Collection<CompletedEvent> exportData() {
        return StreamSupport.stream(completedEventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void importData(Collection<CompletedEvent> data) {
        completedEventRepository.saveAll(data);
    }

    @Override
    public Class<CompletedEvent> entityClass() {
        return CompletedEvent.class;
    }
}
