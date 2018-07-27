package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductSqlRepository implements GenericRepository {
    private SessionFactory factory;

    @Autowired
    public ProductSqlRepository(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public Product getById(int id) {
        Product p = null;
        try(Session session = factory.openSession()){
            session.beginTransaction();
            p = session.get(Product.class,id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return p;
    }
}
