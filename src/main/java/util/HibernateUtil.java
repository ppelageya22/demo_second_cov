package util;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
            try {
                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Books.class)
                        .addAnnotatedClass(Cards.class)
                        .addAnnotatedClass(Read.class)
                        .addAnnotatedClass(Copy.class)
                        .buildMetadata()
                        .buildSessionFactory();
            }
            catch (Exception e) {
                // StandardServiceRegistryBuilder.destroy(registry);
                // e.printStackTrace();
                throw e;
            }
        }
        return sessionFactory;
    }
}