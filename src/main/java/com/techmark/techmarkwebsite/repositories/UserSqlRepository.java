package com.techmark.techmarkwebsite.repositories;

import com.techmark.techmarkwebsite.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserSqlRepository implements GenericRepository {
    private SessionFactory factory;

    @Autowired
    public UserSqlRepository(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User getById(int id) {
        User u = null;
        try(Session session = factory.openSession()){
            session.beginTransaction();
            u = session.get(User.class,id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return u;

    }
}
