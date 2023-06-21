package dao;

import java.util.List;

public interface Service<T> {
    void create(Object ... newData);
    List<T> showAll();
    void update(Object id, Object ... newData);
    void deleteById(Object id);
}
