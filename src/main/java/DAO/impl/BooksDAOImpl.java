package DAO.impl;

import models.Copy;
import models.Books;
import org.hibernate.Session;
import org.hibernate.query.Query;
import DAO.BooksDAO;
import models.Cards;
import util.DAOFactory;
import util.HibernateUtil;

import java.util.List;

import static util.HibernateUtil.*;

public class BooksDAOImpl extends BooksDAO {
    public void addBook(Books book) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(book);
        session.getTransaction().commit();
        session.beginTransaction();
        for(int i = 0; i < book.getAmount(); i++) {
            DAOFactory.getInstance().getCopyDAO().addCopy(new Copy(book));
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateBook(Books book) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(book);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteBook(Books book) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(book);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Books getBookById(Long id) {
        Books result = null;
        Session session = getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE book_id = :param", Books.class)
                .setParameter("param", id);
        if (query.getResultList().size() != 0) {
            result = query.getSingleResult();
        }
        return result;
    }

    @Override
    public List<Books> getBookByName(String name) {
        List<Books> result = null;
        Session session = getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books WHERE name LIKE :param", Books.class)
                .setParameter("param", "%" + name + "%");
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<Cards> getReadersByBook(String name) {
        java.util.List<Cards> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Cards> query = session.createQuery("Select DISTINCT rd " +
                        "FROM Read rec " +
                        "left join rec.reader_id rd " +
                        "left join rec.copy_id cp " +
                        "left join cp.book_id bk " +
                        "Where bk.name LIKE :book", Cards.class)
                .setParameter("book", "%" + name + "%");
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public List<Books> getAllBooks() {
        List<Books> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Books> query = session.createQuery("FROM Books", Books.class);
        if (query.getResultList().size() != 0) {
            result = query.getResultList();
        }
        return result;
    }
}