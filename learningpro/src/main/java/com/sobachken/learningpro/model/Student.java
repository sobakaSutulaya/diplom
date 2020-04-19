package com.sobachken.learningpro.model;

import com.sobachken.learningpro.utils.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Identifiable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String studentCardNumber;
    private String password;
    private String groupName;

}
