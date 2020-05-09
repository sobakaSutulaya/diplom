package com.sobachken.learningpro.model;

import com.sobachken.learningpro.mongomigration.annotation.ChangeLog;
import com.sobachken.learningpro.utils.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@ChangeLog(version = 1)
@Document("students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Identifiable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String password;
    private String groupName;
}
