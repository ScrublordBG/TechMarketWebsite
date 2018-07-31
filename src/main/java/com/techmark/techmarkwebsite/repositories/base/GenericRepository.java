package com.techmark.techmarkwebsite.repositories.base;

import java.util.List;

public interface GenericRepository<T> {
    T getById(int id);
    
    List<T> getAll();
}
