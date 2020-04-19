package com.sobachken.learningpro.model;

import com.sobachken.learningpro.utils.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Identifiable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
}
