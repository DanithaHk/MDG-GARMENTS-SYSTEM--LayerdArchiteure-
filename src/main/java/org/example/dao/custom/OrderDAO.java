package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    boolean exist(String id) throws SQLException, ClassNotFoundException;
}
