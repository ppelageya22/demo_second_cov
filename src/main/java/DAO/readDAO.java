package DAO;

import models.read;

public abstract class readDAO {
    public abstract void addRecord(read record);
    public abstract void updateRecord(read records);
    public abstract read returnBook(String surname, String book);
    public abstract read getRecordById(Long id);
}