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
    public void create(Product product) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public Product getById(int productId) {
        Product product = null;
        try(Session session = factory.openSession()){
            session.beginTransaction();
            product = session.get(Product.class,productId);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return product;
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
    
    @Override
    public void update(int productId, Product updateProduct) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, productId);
            product.setName(updateProduct.getName());
            product.setPrice(updateProduct.getPrice());
            product.setDescription(updateProduct.getDescription());
            product.setImageURL(updateProduct.getImageURL());
            product.setCategory(updateProduct.getCategory());
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void delete(int productId) {
        Product product = getById(productId);
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
