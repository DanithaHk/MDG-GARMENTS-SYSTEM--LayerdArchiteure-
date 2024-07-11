package org.example.dao.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.OrderDeatilsDAO;
import org.example.entity.OrderDeatail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDeatilsDAOImpl implements OrderDeatilsDAO {
    @Override
    public ArrayList<OrderDeatail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(OrderDeatail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO order_detail (oid, cid, contactNumber, pid, productName , unitPrice, qty,  date, nededSwingCloth ,total) VALUES (?,?,? ,?,?,? ,?,?,? ,?)", entity.getOid(), entity.getCid(), entity.getCNumber(),
                entity.getPid(),entity.getPName(),entity.getUnitPrice(),entity.getQty(),entity.getDate(),entity.getNededSwingCloth(),entity.getTotal());

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDeatail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDeatail entity) throws SQLException, ClassNotFoundException {
        return false;
    }
}
