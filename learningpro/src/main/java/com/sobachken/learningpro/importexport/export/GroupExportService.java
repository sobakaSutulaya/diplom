package com.sobachken.learningpro.importexport.export;

import com.sobachken.learningpro.model.Group;
import com.sobachken.learningpro.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GroupExportService implements Exportable<Group> {

    private final GroupRepository groupRepository;

    public GroupExportService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Collection<Group> exportData() {
        return StreamSupport.stream(groupRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void importData(Collection<Group> data) {
        groupRepository.saveAll(data);
    }

    @Override
    public Class<Group> entityClass() {
        return Group.class;
    }
}
