package org.bsuir.dao.common;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> getById(Long id);

    List<T> getAll();

    int save(T item);

    int removeById(Long id);
}
