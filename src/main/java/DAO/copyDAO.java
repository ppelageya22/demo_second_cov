package DAO;

import models.copy;

public abstract class copyDAO {
    public abstract void addCopy(copy copy);
    public abstract void updateCopy(copy copy);
    public abstract void deleteCopy(copy copy);
    public abstract copy GetBookCopyByBookName(String name);
    public abstract copy getCopyById(Long id);
}