package com.techmark.techmarkwebsite;

import com.techmark.techmarkwebsite.models.Category;
import com.techmark.techmarkwebsite.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TechmarkwebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechmarkwebsiteApplication.class, args);
        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        Category c = session.get(Category.class, 1);

        System.out.println(c.getName());
        for (Product p : c.getProducts()) {
            System.out.println(p.getName());

        }
        session.getTransaction().commit();
        session.close();

    }
}


    /*  @Bean
    public SessionFactory createSessionFactory() {
        System.out.println("SessionFactory was created.");
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Category.class)
                .buildSessionFactory();

    }
}
*/