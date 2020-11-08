package ru.itis.xokken.Repositories;

import java.util.List;

public interface CrudRepository<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);

    List<T> findAll();
}
