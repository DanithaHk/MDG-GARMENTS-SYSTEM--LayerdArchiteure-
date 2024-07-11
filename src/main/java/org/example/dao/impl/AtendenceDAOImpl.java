package org.example.dao.impl;

import org.example.dao.AttendenceNesessaryDAO;
import org.example.dao.SQLUtil;
import org.example.dao.custom.AtendenceDAO;
import org.example.dto.AtendenctDTO;
import org.example.entity.Atendence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtendenceDAOImpl implements AtendenceDAO, AttendenceNesessaryDAO {
    @Override
    public ArrayList<Atendence> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Atendence> allAtendence = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM attendance");
        while (rst.next()) {
            Atendence atendence = new Atendence(rst.getString("aid"), rst.getString("name"), rst.getString("date"),rst.getInt("presentOrNot"),rst.getString("eid"));

            allAtendence.add(atendence);

        }
        return allAtendence;
    }

    @Override
    public boolean add(Atendence entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO attendance (aid,name, date, presentOrNot, eid) VALUES (?,?,?,?,?)",entity .getId(), entity.getName(), entity.getDate(),entity.getPresentOrNot(),entity.getEmployeeid());

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT aid FROM attendance ORDER BY aid DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("aid");
            int newCustomerId = Integer.parseInt(id.replace("A000", "")) + 1;
            return String.format("A000", newCustomerId);
        } else {
            return "A001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getIds() {
        return null;
    }

    @Override
    public Atendence search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }



    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(Atendence entity) {
        return false;
    }

    @Override
    public Atendence searchByEId(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM attendance WHERE eid = ?", id+"");
        rst.next();
        return  new Atendence(id+"",rst.getString("name"),rst.getString("date"),rst.getInt("presentOrNot"),rst.getString("eid"));
    }

    @Override
    public int getdateCount(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT Count(presentOrNot) FROM attendance WHERE eid = ?", id + "");
        int i = 0;
        while (rst.next()) {
            return i = rst.getInt(1);
        }
    return  i ;
    }
    }

