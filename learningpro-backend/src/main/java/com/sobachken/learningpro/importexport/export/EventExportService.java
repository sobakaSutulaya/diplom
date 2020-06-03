package com.sobachken.learningpro.importexport.export;

import com.sobachken.learningpro.model.event.Event;
import com.sobachken.learningpro.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventExportService implements Exportable<Event> {

    private final EventRepository eventRepository;

    public EventExportService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Collection<Event> exportData() {
        return StreamSupport.stream(this.eventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void importData(Collection<Event> data) {
        this.eventRepository.saveAll(data);
    }

    @Override
    public Class<Event> entityClass() {
        return Event.class;
    }
}
