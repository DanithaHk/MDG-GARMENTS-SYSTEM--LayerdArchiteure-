package org.example.dao.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.OrderDAO;
import org.example.entity.Employee;
import org.example.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Order> allOrdrs = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders");
        while (rst.next()) {
            Order order = new Order(rst.getString("oid"), rst.getString("name"), rst.getString("date"),rst.getInt("qty"),rst.getString("cid"));
            allOrdrs.add(order);

        }
        return allOrdrs;
    }

    @Override
    public boolean add(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders (oid, name, date, qty, cid ) VALUES (?,?,?,?,?)", entity.getOId(), entity.getName(), entity.getDate(),
                entity.getQty(),entity.getCid());

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM orders WHERE oid=?", id);
        return rst.next();

    }
}
