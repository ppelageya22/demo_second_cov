package DAO;

import models.Read;

public abstract class ReadDAO {
    public abstract void addRecord(Read record);
    public abstract void updateRecord(Read records);
    public abstract Read returnBook(String surname, String book);
    public abstract Read getRecordById(Long id);
}