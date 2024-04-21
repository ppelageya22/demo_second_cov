package DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import DAO.cardsDAO;
import models.books;
import models.cards;
import util.HibernateUtil;

import java.util.List;

import static util.HibernateUtil.getSessionFactory;

public class cardsDAOImpl extends cardsDAO {
    public void addcards(cards cards) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(cards);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updatecards(cards cards) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(cards);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deletecards(cards cards) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(cards);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public cards getcardsById(Long id) {
        cards result = null;
        Session session = getSessionFactory().openSession();
        Query<cards> query = session.createQuery("FROM models.cards WHERE cards_id = :param", cards.class)
                .setParameter("param", id);
        if (query.getResultList().size() != 0) {
            result = query.getSingleResult();
        }
        return result;
    }

    @Override
    public List<cards> getcardsBySurname(String surname) {
        List<cards> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<cards> query = session.createQuery("FROM models.cards WHERE surname LIKE :surname", cards.class)
                .setParameter("surname", "%" + surname + "%");
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<cards> getcardsBooksBySurname(String surname) {
        java.util.List<cards> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<cards> query = session.createQuery("Select DISTINCT bok " +
                        "FROM models.read rec " +
                        "left join rec.cards_id read " +
                        "left join rec.copy_id cop " +
                        "left join cop.book_id bok " +
                        "Where read.surname LIKE :surname", cards.class)
                .setParameter("surname", "%" + surname + "%");
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<cards> getAllcards() {
        List<cards> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<cards> query = session.createQuery("FROM models.cards", cards.class);
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<books> getTakencardsBooks (String surname) {
        List<books> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<books> query = session.createQuery("Select bok " +
                        "FROM models.read rec " +
                        "left join rec.cards_id read " +
                        "left join rec.copy_id cop " +
                        "left join cop.book_id bok " +
                        "Where read.surname LIKE :surname " +
                        "and cop.is_taken_now LIKE 'Yes'", books.class)
                .setParameter("surname", "%" + surname + "%");
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<cards> getcardsWithBooks () {
        List<cards> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<cards> query = session.createQuery("SELECT DISTINCT read " +
                "FROM models.read rec " +
                "LEFT JOIN rec.cards_id read " +
                "LEFT JOIN rec.copy_id cop " +
                "WHERE cop.is_taken_now LIKE 'Yes' " +
                "AND rec.returning_date is NULL", cards.class);
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }
}