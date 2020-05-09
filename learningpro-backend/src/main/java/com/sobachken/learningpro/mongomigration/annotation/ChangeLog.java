package com.sobachken.learningpro.mongomigration.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * ChangeLog annotation used to mark entity classes and version of class should be increased every time that class changed
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeLog {
    long version();

    int order() default 0;
}
