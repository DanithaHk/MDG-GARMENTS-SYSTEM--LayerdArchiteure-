package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Client;

import java.sql.SQLException;

public interface ClientDAO extends CrudDAO <Client> {
    public Client getCustomerList(String cNumber) throws SQLException, ClassNotFoundException;

}
