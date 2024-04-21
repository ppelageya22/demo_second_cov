package DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import models.Read;
import models.Copy;
import DAO.ReadDAO;

import java.util.Date;

import static util.HibernateUtil.getSessionFactory;
import util.DAOFactory;

public class ReadDAOImpl extends ReadDAO {
    public void addRecord(Read record) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(record);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateRecord(Read record) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(record);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Read returnBook(String surname, String book){
        Session session = getSessionFactory().openSession();
        Query<Read> query = session.createQuery("SELECT rec " +
                        "FROM models.Read rec " +
                        "LEFT JOIN rec.reader_id rd " +
                        "LEFT JOIN rec.copy_id cp " +
                        "LEFT JOIN cp.book_id bk " +
                        "WHERE bk.name LIKE :book " +
                        "AND rd.surname LIKE :surname " +
                        "AND cp.is_taken_now LIKE 'Yes'", Read.class)
                .setParameter("book", book).setParameter("surname", surname);
        Read record = query.getSingleResult();
        session.close();

        session = getSessionFactory().openSession();
        Query<Copy> query2 = session.createQuery("SELECT cp " +
                        "FROM models.Read rec " +
                        "LEFT JOIN rec.copy_id cp " +
                        "LEFT JOIN rec.reader_id read " +
                        "LEFT JOIN cp.book_id bk " +
                        "WHERE cp.is_taken_now LIKE 'Yes' " +
                        "AND bk.name LIKE :book " +
                        "AND read.surname LIKE :surname", Copy.class)
                .setParameter("book", book).setParameter("surname", surname);
        Copy copy = query2.getSingleResult();

        Date current_date = new Date(System.currentTimeMillis());
        record.setReturning_date(current_date);
        copy.setIs_taken_now("No");
        DAOFactory.getInstance().getCopyDAO().updateCopy(copy);
        DAOFactory.getInstance().getRecordsDAO().updateRecord(record);
        return record;
    }
    @Override
    public Read getRecordById(Long id) {
        Read result = null;
        Session session = getSessionFactory().openSession();
        Query<Read> query = session.createQuery("FROM models.Read WHERE record_id = :param", Read.class)
                .setParameter("param", id);
        if (query.getResultList().size() != 0) {
            result = query.getResultList().get(0);
        }
        return result;
    }
}