package DAO;

import models.Books;
import models.Cards;

import java.util.List;

public abstract class CardsDAO {
    public abstract void addcards(Cards reader);
    public abstract void deletecards(Cards reader);
    public abstract void updatecards(Cards reader);
    public abstract Cards getcardsById(Long id);
    public abstract List<Cards> getcardsBySurname(String name);
    public abstract List<Cards> getcardsBooksBySurname(String name);
    public abstract List<Cards> getAllcards();
    public abstract List<Books> getTakencardsBooks(String surname);
    public abstract List<Cards> getcardsWithBooks();
}