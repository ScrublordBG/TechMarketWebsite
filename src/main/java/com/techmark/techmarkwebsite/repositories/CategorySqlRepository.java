package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.repositories.base.CategoryRepository;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategorySqlRepository implements CategoryRepository {

    private SessionFactory factory;

    @Autowired
    public CategorySqlRepository(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Product> getAllByCategoryId(int id) {
        List<Product> products = new ArrayList<>();
        try(Session session = factory.openSession()){
            session.beginTransaction();
            //products = session.get(Category.class,id).getProducts();
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return products;
    }
}
