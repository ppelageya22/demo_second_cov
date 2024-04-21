package DAO;

import models.books;
import models.cards;

import java.util.List;

public abstract class booksDAO {
    public abstract void addBook(books book);
    public abstract void deleteBook(books book);
    public abstract void updateBook(books book);
    public abstract books getBookById(Long id);
    public abstract List<books> getBookByName(String name);
    public abstract List<cards> getReadersByBook(String name);
    public abstract List<books> getAllBooks();
}