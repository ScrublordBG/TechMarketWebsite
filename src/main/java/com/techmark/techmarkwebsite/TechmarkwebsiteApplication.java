package com.techmark.techmarkwebsite;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Order;
import com.techmark.techmarkwebsite.models.Product;
import com.techmark.techmarkwebsite.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@SpringBootApplication
public class TechmarkwebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechmarkwebsiteApplication.class, args);

    }


    @Bean
    public SessionFactory createSessionFactory() {
        System.out.println("SessionFactory was created.");
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();

    }
}
