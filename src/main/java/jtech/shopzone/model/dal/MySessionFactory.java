package jtech.shopzone.model.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessionFactory {

    private static MySessionFactory mySessionFactory;
    private static SessionFactory sessionFactory;

    private MySessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static MySessionFactory getMySessionFactory() {
        return new MySessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
        
    }

}
