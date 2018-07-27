package com.techmark.techmarkwebsite.services;

public interface GenericService<T> {
    T getById(int id);
}
