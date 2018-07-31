package com.techmark.techmarkwebsite.services.base;

import java.util.List;

public interface GenericService<T> {
    
    void create(T obj);
    
    T getById(int id);
    
    List<T> getAll();
    
    void update(T obj);
    
    void delete(int id);
}
