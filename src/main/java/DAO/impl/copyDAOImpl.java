package DAO.impl;

import models.copy;
import org.hibernate.Session;
import org.hibernate.query.Query;
import DAO.copyDAO;

import static util.HibernateUtil.*;

public class copyDAOImpl extends copyDAO {
    public void addCopy(copy copy) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(copy);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateCopy(copy copy) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(copy);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteCopy(copy copy) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(copy);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public copy GetBookCopyByBookName(String name){
        copy result = null;
        Session session = getSessionFactory().openSession();
        Query<copy> query = session.createQuery("Select cp " +
                        "FROM models.copy cp " +
                        "LEFT JOIN cp.book_id bok " +
                        "WHERE cp.is_taken_now LIKE 'No' " +
                        "and bok.name LIKE :param", copy.class)
                .setParameter("param", name);
        if (query.getResultList().size() != 0) {
            result = query.getResultList().get(0);
        }
        return result;
    }

    @Override
    public copy getCopyById(Long id) {
        copy copy = null;
        Session session = getSessionFactory().openSession();
        Query<copy> query = session.createQuery("FROM models.copy WHERE copy_id = :param",
                copy.class).setParameter("param", id);
        if (query.getResultList().size() != 0) {
            copy = query.getSingleResult();
        }
        return copy;
    }
}