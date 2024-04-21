package DAO;

import models.books;
import models.cards;

import java.util.List;

public abstract class cardsDAO {
    public abstract void addcards(cards reader);
    public abstract void deletecards(cards reader);
    public abstract void updatecards(cards reader);
    public abstract cards getcardsById(Long id);
    public abstract List<cards> getcardsBySurname(String name);
    public abstract List<cards> getcardsBooksBySurname(String name);
    public abstract List<cards> getAllcards();
    public abstract List<books> getTakencardsBooks(String surname);
    public abstract List<cards> getcardsWithBooks();
}