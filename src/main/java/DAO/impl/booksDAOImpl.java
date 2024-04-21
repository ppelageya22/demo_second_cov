package DAO.impl;

import models.copy;
import models.books;
import org.hibernate.Session;
import org.hibernate.query.Query;
import DAO.booksDAO;
import models.cards;
import util.DAOFactory;
import util.HibernateUtil;

import java.util.List;

import static util.HibernateUtil.*;

public class booksDAOImpl extends booksDAO {
    public void addBook(books book) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(book);
        session.getTransaction().commit();
        session.beginTransaction();
        for(int i = 0; i < book.getAmount(); i++) {
            DAOFactory.getInstance().getCopyDAO().addCopy(new copy(book));
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateBook(books book) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(book);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteBook(books book) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(book);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public books getBookById(Long id) {
        books result = null;
        Session session = getSessionFactory().openSession();
        Query<books> query = session.createQuery("FROM models.books WHERE books.book_id = :param", books.class)
                .setParameter("param", id);
        if (query.getResultList().size() != 0) {
            result = query.getSingleResult();
        }
        return result;
    }

    @Override
    public List<books> getBookByName(String name) {
        List<books> result = null;
        Session session = getSessionFactory().openSession();
        Query<books> query = session.createQuery("FROM models.books WHERE name LIKE :param", books.class)
                .setParameter("param", "%" + name + "%");
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<cards> getReadersByBook(String name) {
        java.util.List<cards> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<cards> query = session.createQuery("Select DISTINCT rd " +
                        "FROM models.read rec " +
                        "left join rec.reader_id rd " +
                        "left join rec.copy_id cp " +
                        "left join cp.book_id bk " +
                        "Where bk.name LIKE :book", cards.class)
                .setParameter("book", "%" + name + "%");
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<books> getAllBooks() {
        List<books> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<books> query = session.createQuery("FROM models.books", books.class);
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }
}