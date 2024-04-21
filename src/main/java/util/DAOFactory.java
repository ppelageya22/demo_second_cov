package util;

import DAO.booksDAO;
import DAO.cardsDAO;
import DAO.readDAO;
import DAO.copyDAO;
import DAO.impl.booksDAOImpl;
import DAO.impl.cardsDAOImpl;
import DAO.impl.copyDAOImpl;
import DAO.impl.readDAOImpl;

public class DAOFactory {
    private static cardsDAO readerDAO = null;
    private static booksDAO booksDAO = null;
    private static readDAO recordsDAO = null;
    private static copyDAO copyDAO = null;
    private static DAOFactory instance = null;

    public static synchronized DAOFactory getInstance(){
        if (instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }

    public cardsDAO getReaderDAO(){
        if (readerDAO == null){
            readerDAO = new cardsDAOImpl();
        }
        return readerDAO;
    }

    public booksDAO getBooksDAO(){
        if (booksDAO == null){
            booksDAO = new booksDAOImpl();
        }
        return booksDAO;
    }

    public readDAO getRecordsDAO(){
        if (recordsDAO == null){
            recordsDAO = new readDAOImpl();
        }
        return recordsDAO;
    }

    public copyDAO getCopyDAO(){
        if (copyDAO == null){
            copyDAO = new copyDAOImpl();
        }
        return copyDAO;
    }
}