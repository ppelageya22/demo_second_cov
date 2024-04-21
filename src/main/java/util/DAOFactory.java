package util;

import DAO.BooksDAO;
import DAO.CardsDAO;
import DAO.ReadDAO;
import DAO.CopyDAO;
import DAO.impl.BooksDAOImpl;
import DAO.impl.CardsDAOImpl;
import DAO.impl.CopyDAOImpl;
import DAO.impl.ReadDAOImpl;

public class DAOFactory {
    private static CardsDAO readerDAO = null;
    private static BooksDAO booksDAO = null;
    private static ReadDAO recordsDAO = null;
    private static CopyDAO copyDAO = null;
    private static DAOFactory instance = null;

    public static synchronized DAOFactory getInstance(){
        if (instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }

    public CardsDAO getReaderDAO(){
        if (readerDAO == null){
            readerDAO = new CardsDAOImpl();
        }
        return readerDAO;
    }

    public BooksDAO getBooksDAO(){
        if (booksDAO == null){
            booksDAO = new BooksDAOImpl();
        }
        return booksDAO;
    }

    public ReadDAO getRecordsDAO(){
        if (recordsDAO == null){
            recordsDAO = new ReadDAOImpl();
        }
        return recordsDAO;
    }

    public CopyDAO getCopyDAO(){
        if (copyDAO == null){
            copyDAO = new CopyDAOImpl();
        }
        return copyDAO;
    }
}