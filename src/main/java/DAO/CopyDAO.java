package DAO;

import models.Copy;

public abstract class CopyDAO {
    public abstract void addCopy(Copy copy);
    public abstract void updateCopy(Copy copy);
    public abstract void deleteCopy(Copy copy);
    public abstract Copy GetBookCopyByBookName(String name);
    public abstract Copy getCopyById(Long id);
}