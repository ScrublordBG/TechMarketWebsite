package com.techmark.techmarkwebsite;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TechmarkwebsiteApplication {

    public static void main(String[] args) {

    }


    @Bean
    public SessionFactory createSessionFactory() {
        System.out.println("SessionFactory was created.");
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

    }
}