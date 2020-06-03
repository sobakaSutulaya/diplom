package com.sobachken.learningpro.executeengine.controller;

import com.sobachken.learningpro.executeengine.service.ExecuteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.UUID;

import static com.sobachken.learningpro.common.ApiPath.EXECUTE_ENGINE_V1_PATH;
import static com.sobachken.learningpro.common.ApiPath.EXECUTE_PATH;

@RestController
@RequestMapping(EXECUTE_ENGINE_V1_PATH)
public class ExecuteController {

    private final ExecuteService executeService;

    public ExecuteController(ExecuteService executeService) {
        this.executeService = executeService;
    }

    @PostMapping("/{taskId}" + EXECUTE_PATH)
    public ResponseEntity execute(@PathVariable UUID taskId, MultipartRequest multipartRequest) throws Exception {
        this.executeService.execute(multipartRequest, taskId);
        return ResponseEntity.ok().build();
    }


}
