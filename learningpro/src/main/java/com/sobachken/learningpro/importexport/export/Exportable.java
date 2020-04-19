package com.sobachken.learningpro.importexport.export;

import com.sobachken.learningpro.utils.Identifiable;

import java.util.Collection;

public interface Exportable<T extends Identifiable> {

    Collection<T> exportData();

    void importData(Collection<T> data);

    Class<T> entityClass();
}
