package jtech.shopzone.model.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessionFactory {

    private static MySessionFactory mySessionFactory;
    private static SessionFactory sessionFactory;
    private static Session session;

    private MySessionFactory(){}

    public static MySessionFactory getMySessionFactory(){
        sessionFactory = new Configuration()
                .configure("/hibernate.cfg.xml").buildSessionFactory();
        return mySessionFactory;
    }

    public Session getSession(){
        session = sessionFactory.openSession();
        return session;
    }

}
