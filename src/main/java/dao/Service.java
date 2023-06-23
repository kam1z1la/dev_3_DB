package dao;

import java.util.List;

public interface Service<T> {
    void create(T entityClass);

    List<T> showAll();

    boolean update(T entityClass);

    boolean delete(T entityClass);
}
