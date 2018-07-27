package com.techmark.techmarkwebsite.repositories;

public interface GenericRepository<T> {
    T getById(int id);
}
