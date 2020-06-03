package com.sobachken.learningpro.executeengine.service;

import com.sobachken.learningpro.model.task.Task;
import com.sobachken.learningpro.model.task.TaskAnswer;
import com.sobachken.learningpro.model.task.TaskAnswer.InputToOutputArgument;
import com.sobachken.learningpro.service.TaskAnswerService;
import com.sobachken.learningpro.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ExecuteService {

    private static final String JAVA_FILE = "java_file.java";

    private final TaskService taskService;
    private final TaskAnswerService taskAnswerService;

    public ExecuteService(TaskService taskService, TaskAnswerService taskAnswerService) {
        this.taskAnswerService = taskAnswerService;
        this.taskService = taskService;
    }

    public void execute(MultipartRequest multipartRequest, UUID taskId) throws IOException {
        File javaFile = this.getFileFromMultipart(multipartRequest);
        Task task = taskService.getTask(taskId);

        TaskAnswer taskAnswer = taskAnswerService.getTaskAnswerByTaskId(task.getId());

    }

    private File getFileFromMultipart(MultipartRequest multipartRequest) {
        return null;
    }

    private List<InputToOutputArgument> getInputToOutputArgumentsFromExecution(File javaFile) {
        return null;
    }

    private void writeFile() {

    }
}
