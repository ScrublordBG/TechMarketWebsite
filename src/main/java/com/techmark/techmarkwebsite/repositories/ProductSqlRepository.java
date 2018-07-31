package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductSqlRepository implements GenericRepository<Product> {
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
    
    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            products = session.createQuery("from Product").list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return products;
    }
}
