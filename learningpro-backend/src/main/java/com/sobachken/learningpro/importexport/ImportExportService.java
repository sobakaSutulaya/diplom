package com.sobachken.learningpro.importexport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Multimap;
import com.sobachken.learningpro.importexport.export.Exportable;
import com.sobachken.learningpro.utils.Identifiable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
public class ImportExportService {

    private Set<Exportable<?>> exportableServices;
    private ObjectMapper objectMapper;

    public ImportExportService(Set<Exportable<?>> exportServices) {
        this.exportableServices = exportServices;
        this.objectMapper = new ObjectMapper();
    }

    public byte[] exportData() throws IOException {
        log.debug("Exporting all");
        try (final ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
             final ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayInputStream)) {

            for (Exportable<?> exportable : exportableServices) {
                for (Identifiable identifiable : exportable.exportData()) {
                    String fileName = exportable.entityClass().getSimpleName() + "_" + identifiable.getId() + ".json";
                    log.debug("Exporting file : '{}'", fileName);
                    String fileContent = objectMapper.writeValueAsString(identifiable);
                    addFile(zipOutputStream, fileName, fileContent);
                }
            }

            zipOutputStream.close();
            log.debug("Zip stream has been finished");
            return byteArrayInputStream.toByteArray();
        }
    }

    private void addFile(ZipOutputStream zipOutputStream, String fileName, String fileContent) throws IOException {
        try (final InputStream stream = new ByteArrayInputStream(fileContent.getBytes(UTF_8))) {
            final byte[] buffer = new byte[1024];
            zipOutputStream.putNextEntry(new ZipEntry(fileName));
            int length;

            while ((length = stream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, length);
            }

            zipOutputStream.closeEntry();
        } catch (IOException ex) {
            throw new IOException("Failed to add file with name '" + fileName + "' to zup during the export", ex);
        }
    }


    public void importData(InputStream importStream) throws IOException {
        Map<Class, List<Identifiable>> entitiesMap = new HashMap<>();
        try {
            try (ZipInputStream inputStream = new ZipInputStream(importStream)) {
                ZipEntry zipEntry;
                while ((zipEntry = inputStream.getNextEntry()) != null) {
                    log.debug("Read file : '{}'", zipEntry.getName());
                    final String fileImportIdentificator = zipEntry.getName().substring(0, zipEntry.getName().indexOf("_"));
                    String entryJson = IOUtils.toString(inputStream, UTF_8);

                    Class<? extends Identifiable> entityClass = findEntityClass(fileImportIdentificator);
                    if (entityClass == null) {
                        throw new IllegalStateException("Can't find approriate importService for entity type '" + fileImportIdentificator + "'");
                    }

                    Identifiable entity = objectMapper.readValue(entryJson, entityClass);
                    if (!entitiesMap.containsKey(entityClass)) {
                        entitiesMap.put(entityClass, new ArrayList<>());
                    }
                    entitiesMap.get(entityClass).add(entity);
                }
            }
        } catch (IOException ex) {
            log.error("Fail while read imported archive with message : '{}'", ex.getMessage());
            throw ex;
        }
        importEntities(entitiesMap);
    }

    @NotNull
    private void importEntities(Map<Class, List<Identifiable>> entitiesMap) {
        for (Exportable<?> exportable : exportableServices) {
            Collection collection = entitiesMap.get(exportable.entityClass());
            log.debug("Importing entities with type '{}'", exportable.entityClass().getSimpleName());
            if (collection == null) {
                log.debug("Skip import because there is no entites");
            }
        }
    }

    @Nullable
    private Class<? extends Identifiable> findEntityClass(String fileImportIdentificator) {
        for (Exportable<?> exportable : exportableServices) {
            String serviceImportIdentificator = exportable.entityClass().getSimpleName();
            if (serviceImportIdentificator.equals(fileImportIdentificator)) {
                return exportable.entityClass();
            }
        }
        return null;
    }

}
