package com.sobachken.learningpro.common.exception.migration;

public class MongoMigrationException extends RuntimeException {

    public MongoMigrationException(Throwable throwable) {
        super(throwable);
    }
}
