package com.sobachken.learningpro.model.task;

import com.sobachken.learningpro.mongomigration.annotation.ChangeLog;
import com.sobachken.learningpro.utils.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Document("taskAnswers")
@ChangeLog(version = 2)
@AllArgsConstructor
@NoArgsConstructor
public class TaskAnswer implements Identifiable {
    private UUID id;
    private UUID taskId;
    List<InputToOutputArgument> expectedResult;
//    private Map<InputArgument, OutputArgument> expectedResult;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InputToOutputArgument {
        private String inputArgument;
        private String outputArgument;
    }

}


