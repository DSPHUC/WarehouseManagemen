package com.cg.service;

import java.util.List;

public interface CRUD<T> {
    List<T> getAll();
    T findById(long id);
    T findByName(String name);

    void creat(T t);

    void edit(long id, T t);
    void remove(long id);
    boolean isExist(long id);


}
