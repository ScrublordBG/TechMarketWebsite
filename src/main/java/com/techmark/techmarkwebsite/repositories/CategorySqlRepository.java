package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.repositories.base.CategoryRepository;
import com.techmark.techmarkwebsite.repositories.base.GenericRepository;
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
    public List<Product> getAllByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();
        try(Session session = factory.openSession()){
            session.beginTransaction();
            products = session.get(Category.class,categoryId).getProducts();
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return products;
    }
    
    @Override
    public void create(Category category) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public Category getById(int categoryId) {
        Category category = null;
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            category = session.get(Category.class, categoryId);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return category;
    }
    
    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            categories = session.createQuery("from Category").list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }
    
    @Override
    public void update(int categoryId, Category updatedCategory) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, categoryId);
            category.setName(updatedCategory.getName());
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void delete(int categoryId) {
        Category category = getById(categoryId);
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(category);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
