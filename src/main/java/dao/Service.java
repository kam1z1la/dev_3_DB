package dao;

import java.util.List;

public interface Service<T> {
    String getById(long id);
    void deleteById(long id);
    List<T> listAll();
}
