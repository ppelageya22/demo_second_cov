package daotest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import models.books;
import models.copy;
import models.cards;
import models.read;
import util.DAOFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = booksDAOTest.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class booksDAOTest {

    @Test
    void contextLoads() {
    }

    @Test
    public void deleteсardsTest () {
        DAOFactory.getInstance().getReaderDAO().addcards(new cards("Ева", "Катко", "Сергеевна", "2020-01-01",
                "1997-08-22", "Новгород Державина д4", "88493732"));
        cards reader = DAOFactory.getInstance().getReaderDAO().getcardsBySurname("Катко").get(0);
        DAOFactory.getInstance().getReaderDAO().deletecards(reader);
        List<cards> reader1 = DAOFactory.getInstance().getReaderDAO().getcardsBySurname("Катко");
        assertNull(reader1);
    }

    @Test
    public void addcardsTest () {
        DAOFactory.getInstance().getReaderDAO().addcards(new cards("Ева", "Катко", "Сергеевна", "2020-01-01",
                "1997-08-22", "Новгород Державина д4", "88493732"));
        cards reader = DAOFactory.getInstance().getReaderDAO().getcardsBySurname("Катко").get(0);
        assertNotNull(reader);
        reader = DAOFactory.getInstance().getReaderDAO().getcardsBySurname("Катко").get(0);
        DAOFactory.getInstance().getReaderDAO().deletecards(reader);
    }

    @Test
    public void getcardsBySurnameTest () {
        List<cards> reader = DAOFactory.getInstance().getReaderDAO().getcardsBySurname("Лисакова");
        assertNotNull(reader);
    }

    @Test
    public void getcardsByIDTest () {
        cards reader = DAOFactory.getInstance().getReaderDAO().getcardsById(5L);
        assertNotNull(reader);
    }

    @Test
    public void getBookByNameTest () {
        List<books> book = DAOFactory.getInstance().getBooksDAO().getBookByName("Живаго");
        assertNotNull(book);
    }

    @Test
    public void getBookByIDTest () {
        books book = DAOFactory.getInstance().getBooksDAO().getBookById(1L);
        assertNotNull(book);
    }

    @Test
    public void addBookTest () {
        DAOFactory.getInstance().getBooksDAO().addBook(new books("Контакт11", "Карл Саган",
                "Альпина нон-фикшн", 10, "роман", "21-04-2023"));
        books book = DAOFactory.getInstance().getBooksDAO().getBookByName("Контакт11").get(0);
        assertNotNull(book);
        book = DAOFactory.getInstance().getBooksDAO().getBookByName("Контакт11").get(0);
        DAOFactory.getInstance().getBooksDAO().deleteBook(book);
    }

    @Test
    public void deleteBookTest () {
        DAOFactory.getInstance().getBooksDAO().addBook(new books("Жизнь", "Данкин",
                "путешествия", 5, "Книга про путешествия на короблях",
                "роман", "21-04-2023"));
        books book = DAOFactory.getInstance().getBooksDAO().getBookByName("Жизнь").get(0);
        DAOFactory.getInstance().getBooksDAO().deleteBook(book);
        List<books> book1 = DAOFactory.getInstance().getBooksDAO().getBookByName("Жизнь");
        assertNull(book1);
    }

    @Test
    public void getcardsBooks () {
        List<cards> books = DAOFactory.getInstance().getReaderDAO().getcardsBooksBySurname("Лисакова");
        assertNotNull(books);
    }

    @Test
    public void getBooksReaders () {
        List<cards> readers = DAOFactory.getInstance().getBooksDAO().getReadersByBook("Живаго");
        assertNotNull(readers);
    }

    @Test
    public void updatecardsTest () {
        cards reader = DAOFactory.getInstance().getReaderDAO().getcardsBySurname("Лисакова").get(0);
        reader.setAddress("Питер");
        DAOFactory.getInstance().getReaderDAO().updatecards(reader);
        reader = DAOFactory.getInstance().getReaderDAO().getcardsBySurname("Лисакова").get(0);
        assertEquals("Питер", reader.getAddress());
    }

    @Test
    public void updateBookTest () {
        DAOFactory.getInstance().getBooksDAO().addBook(new books("Жизнь", "Данкин",
                "путешествия", 5, "Книга про путешествия на короблях",
                "роман", "21-04-2023"));
        books book = DAOFactory.getInstance().getBooksDAO().getBookByName("Жизнь").get(0);
        book.setAbout("Про жизнь");
        DAOFactory.getInstance().getBooksDAO().updateBook(book);
        book = DAOFactory.getInstance().getBooksDAO().getBookByName("Жизнь").get(0);
        assertEquals("Про жизнь", book.getAbout());
        DAOFactory.getInstance().getBooksDAO().deleteBook(book);
    }

    @Test
    public void updateCopyTest () {
        copy copy = DAOFactory.getInstance().getCopyDAO().GetBookCopyByBookName("Живаго");
        copy.setIs_taken_now("Yes");
        DAOFactory.getInstance().getCopyDAO().updateCopy(copy);
        assertEquals("Yes", copy.getIs_taken_now());
        copy.setIs_taken_now("No");
        DAOFactory.getInstance().getCopyDAO().updateCopy(copy);
    }

    @Test
    public void getBookCopyByBookNameTest () {
        copy copy = DAOFactory.getInstance().getCopyDAO().GetBookCopyByBookName("Живаго");
        assertNotNull(copy);
    }

    @Test
    public void getCopyByIDTest () {
        copy copy = DAOFactory.getInstance().getCopyDAO().getCopyById(1L);
        assertNotNull(copy);
    }

    @Test
    public void getRecordByIdTest () {
        read record = DAOFactory.getInstance().getRecordsDAO().getRecordById(1L);
        assertNotNull(record);
    }

    @Test
    public void returnBookTest () {
        DAOFactory.getInstance().getRecordsDAO().addRecord(new read("Лисакова",
                "Живаго"));
        read record = DAOFactory.getInstance().getRecordsDAO().returnBook("Лисакова",
                "Живаго");
        Date current_date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        assertEquals(sdf.format(current_date), sdf.format(record.getReturning_date()));
    }

    @Test
    public void deleteCopyTest () {
        Long id = 66L;	// correct value before running tests (+1)
        copy copy = DAOFactory.getInstance().getCopyDAO().getCopyById(id);
        DAOFactory.getInstance().getCopyDAO().deleteCopy(copy);
        copy = DAOFactory.getInstance().getCopyDAO().getCopyById(id);
        assertNull(copy);
    }
}