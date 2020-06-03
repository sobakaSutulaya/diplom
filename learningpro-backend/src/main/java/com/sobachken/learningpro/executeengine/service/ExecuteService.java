package com.sobachken.learningpro.executeengine.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
public class ExecuteService {

    private static String JAVA_FILE = "java_file.java";

    public void execute(MultipartRequest multipartRequest) throws IOException {

    }
}
