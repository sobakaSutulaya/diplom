package com.sobachken.learningpro.importexport;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.sobachken.learningpro.utils.Constants.*;

@Slf4j
@RestController
@RequestMapping(API_V1_IMPORT_EXPORT_PATH)
public class ImportExportController {
    private final String APPLICATION_ZIP = "application/zip";
    private final String CONTENT_DISPOSITION = "Content-Disposition";
    private final String INLINE_FILENAME = "inline; filename=";
    private final String EXPORT_FILENAME = "export-data.zip";

    private ImportExportService importExportService;

    public ImportExportController(ImportExportService importExportService) {
        this.importExportService = importExportService;
    }

    @GetMapping(produces = APPLICATION_ZIP)
    public void exportData(HttpServletResponse httpServletResponse) throws IOException {
        log.info("Start export data");
        httpServletResponse.setContentType(APPLICATION_ZIP);
        httpServletResponse.setHeader(CONTENT_DISPOSITION, INLINE_FILENAME + EXPORT_FILENAME);

        byte[] exportedArchive = importExportService.exportData();
        IOUtils.copy(new ByteArrayInputStream(exportedArchive), httpServletResponse.getOutputStream());
        log.info("Export success");
    }

    @PostMapping
    public ResponseEntity importData(@RequestParam(name = "importFile") MultipartFile file) throws IOException {
        log.info("Start import data");
        importExportService.importData(file.getInputStream());
        return ResponseEntity
                .ok()
                .build();
    }
}
