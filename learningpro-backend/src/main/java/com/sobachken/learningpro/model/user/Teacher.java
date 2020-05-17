package com.sobachken.learningpro.model.user;

import com.sobachken.learningpro.mongomigration.annotation.ChangeLog;
import com.sobachken.learningpro.utils.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@ChangeLog(version = 1)
@Document("teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Identifiable {
    private UUID id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
