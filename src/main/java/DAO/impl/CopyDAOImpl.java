package DAO.impl;

import models.Copy;
import org.hibernate.Session;
import org.hibernate.query.Query;
import DAO.CopyDAO;

import static util.HibernateUtil.*;

public class CopyDAOImpl extends CopyDAO {
    public void addCopy(Copy copy) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(copy);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateCopy(Copy copy) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(copy);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteCopy(Copy copy) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(copy);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Copy GetBookCopyByBookName(String name){
        Copy result = null;
        Session session = getSessionFactory().openSession();
        Query<Copy> query = session.createQuery("Select cp " +
                        "FROM models.Copy cp " +
                        "LEFT JOIN cp.book_id bok " +
                        "WHERE cp.is_taken_now LIKE 'No' " +
                        "and bok.name LIKE :param", Copy.class)
                .setParameter("param", name);
        if (query.getResultList().size() != 0) {
            result = query.getResultList().get(0);
        }
        return result;
    }

    @Override
    public Copy getCopyById(Long id) {
        Copy copy = null;
        Session session = getSessionFactory().openSession();
        Query<Copy> query = session.createQuery("FROM models.Copy WHERE copy_id = :param",
                Copy.class).setParameter("param", id);
        if (query.getResultList().size() != 0) {
            copy = query.getSingleResult();
        }
        return copy;
    }
}