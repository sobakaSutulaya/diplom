package com.sobachken.learningpro.model.task.dto;

import com.sobachken.learningpro.model.task.Task;
import com.sobachken.learningpro.model.task.TaskAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {
    @NotNull
    Task task;
    @NotNull
    TaskAnswer taskAnswer;
}
