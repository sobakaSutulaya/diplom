package com.sobachken.learningpro.model;

import com.sobachken.learningpro.utils.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document("groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group implements Identifiable {
    private UUID id;
    private String groupName;
    @DBRef
    private List<Student> students;
}
