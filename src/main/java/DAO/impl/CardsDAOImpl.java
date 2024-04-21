package DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import DAO.CardsDAO;
import models.Books;
import models.Cards;
import util.HibernateUtil;

import java.util.List;

import static util.HibernateUtil.getSessionFactory;

public class CardsDAOImpl extends CardsDAO {
    public void addcards(Cards cards) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(cards);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updatecards(Cards cards) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(cards);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deletecards(Cards cards) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(cards);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Cards getcardsById(Long id) {
        Cards result = null;
        Session session = getSessionFactory().openSession();
        Query<Cards> query = session.createQuery("FROM models.Cards WHERE cards_id = :param", Cards.class)
                .setParameter("param", id);
        if (query.getResultList().size() != 0) {
            result = query.getSingleResult();
        }
        return result;
    }

    @Override
    public List<Cards> getcardsBySurname(String surname) {
        List<Cards> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Cards> query = session.createQuery("FROM cards WHERE surname LIKE :surname", Cards.class)
                .setParameter("surname", "%" + surname + "%");
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<Cards> getcardsBooksBySurname(String surname) {
        java.util.List<Cards> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Cards> query = session.createQuery("Select DISTINCT bok " +
                        "FROM models.Read rec " +
                        "left join rec.cards_id read " +
                        "left join rec.copy_id cop " +
                        "left join cop.book_id bok " +
                        "Where read.surname LIKE :surname", Cards.class)
                .setParameter("surname", "%" + surname + "%");
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<Cards> getAllcards() {
        List<Cards> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Cards> query = session.createQuery("FROM models.Cards", Cards.class);
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<Books> getTakencardsBooks (String surname) {
        List<Books> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("Select bok " +
                        "FROM models.Read rec " +
                        "left join rec.cards_id read " +
                        "left join rec.copy_id cop " +
                        "left join cop.book_id bok " +
                        "Where read.surname LIKE :surname " +
                        "and cop.is_taken_now LIKE 'Yes'", Books.class)
                .setParameter("surname", "%" + surname + "%");
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<Cards> getcardsWithBooks () {
        List<Cards> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Cards> query = session.createQuery("SELECT DISTINCT read " +
                "FROM models.Read rec " +
                "LEFT JOIN rec.cards_id read " +
                "LEFT JOIN rec.copy_id cop " +
                "WHERE cop.is_taken_now LIKE 'Yes' " +
                "AND rec.returning_date is NULL", Cards.class);
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }
}