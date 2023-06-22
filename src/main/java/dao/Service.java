package dao;

import java.util.List;

public interface Service<T> {
    void create(T entityClass, Object ... newData);
    List<T> showAll();
    <K> void update(T entityClass, K id, Object ... newData);
    <K> void deleteById(K id);
}
