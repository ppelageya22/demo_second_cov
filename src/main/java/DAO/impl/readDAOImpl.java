package DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import models.read;
import models.copy;
import DAO.readDAO;

import java.util.Date;

import static util.HibernateUtil.getSessionFactory;
import util.DAOFactory;

public class readDAOImpl extends readDAO {
    public void addRecord(read record) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(record);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateRecord(read record) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(record);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public read returnBook(String surname, String book){
        Session session = getSessionFactory().openSession();
        Query<read> query = session.createQuery("SELECT rec " +
                        "FROM models.read rec " +
                        "LEFT JOIN rec.reader_id rd " +
                        "LEFT JOIN rec.copy_id cp " +
                        "LEFT JOIN cp.book_id bk " +
                        "WHERE bk.name LIKE :book " +
                        "AND rd.surname LIKE :surname " +
                        "AND cp.is_taken_now LIKE 'Yes'", read.class)
                .setParameter("book", book).setParameter("surname", surname);
        read record = query.getSingleResult();
        session.close();

        session = getSessionFactory().openSession();
        Query<copy> query2 = session.createQuery("SELECT cp " +
                        "FROM models.read rec " +
                        "LEFT JOIN rec.copy_id cp " +
                        "LEFT JOIN rec.reader_id read " +
                        "LEFT JOIN cp.book_id bk " +
                        "WHERE cp.is_taken_now LIKE 'Yes' " +
                        "AND bk.name LIKE :book " +
                        "AND read.surname LIKE :surname", copy.class)
                .setParameter("book", book).setParameter("surname", surname);
        copy copy = query2.getSingleResult();

        Date current_date = new Date(System.currentTimeMillis());
        record.setReturning_date(current_date);
        copy.setIs_taken_now("No");
        DAOFactory.getInstance().getCopyDAO().updateCopy(copy);
        DAOFactory.getInstance().getRecordsDAO().updateRecord(record);
        return record;
    }
    @Override
    public read getRecordById(Long id) {
        read result = null;
        Session session = getSessionFactory().openSession();
        Query<read> query = session.createQuery("FROM models.read WHERE record_id = :param", read.class)
                .setParameter("param", id);
        if (query.getResultList().size() != 0) {
            result = query.getResultList().get(0);
        }
        return result;
    }
}