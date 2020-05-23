package com.sobachken.learningpro.executeengine.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

@Slf4j
@Service
public class ExecuteService {

    private static String JAVA_FILE = "java_file.java";

    public void execute(MultipartRequest multipartRequest) throws IOException {
        multipartRequest.getFile(JAVA_FILE);
    }
}
