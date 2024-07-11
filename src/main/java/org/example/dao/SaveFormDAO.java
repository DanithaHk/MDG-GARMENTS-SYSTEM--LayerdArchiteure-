package org.example.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SaveFormDAO <T> extends SuperDAO{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean add(T entity) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
}
