package com.sobachken.learningpro.mongomigration.annotation;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class ChangeLogAnnotationProcessor {
    private static final String DEFAULT_PACKAGE = "com.sobachken.learningpro";

    public long getActualDBVersion() {
        Set<Class<?>> annotatedClasses = getAnnotatedClasses();
        List<Long> changeLogVersions = getChangeLogVersions(annotatedClasses);
        return findHighestChangeLogVersion(changeLogVersions);
    }

    private long findHighestChangeLogVersion(List<Long> changeLogVersions) {
        return Collections.max(changeLogVersions);
    }

    private List<Long> getChangeLogVersions(Set<Class<?>> annotatedClasses) {
        List<Long> changeLogVersions = new LinkedList<>();
        for (Class<?> annotatedClass : annotatedClasses) {
            changeLogVersions.add(annotatedClass.getAnnotation(ChangeLog.class).version());
        }
        return changeLogVersions;
    }

    private Set<Class<?>> getAnnotatedClasses() {
        log.info("Get classes annotated with '{}'", ChangeLog.class.getSimpleName());
        Reflections reflections = new Reflections(DEFAULT_PACKAGE);
        return reflections.getTypesAnnotatedWith(ChangeLog.class);
    }
}
