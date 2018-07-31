package com.techmark.techmarkwebsite.services.base;

import java.util.List;

public interface GenericService<T> {
    T getById(int id);
    
    List<T> getAll();
}
