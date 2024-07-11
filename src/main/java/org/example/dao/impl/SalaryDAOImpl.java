package org.example.dao.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.SalaryDAO;
import org.example.entity.Product;
import org.example.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> allSalary = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM salary");
        while (rst.next()) {
            Salary salary = new Salary(rst.getString("sid"), rst.getString("eid"), rst.getString("name"),rst.getString("date"),rst.getDouble("basicSalary"),rst.getDouble("bouns"),rst.getDouble("total"));
            allSalary.add(salary);
        }
        return allSalary ;

    }

    @Override
    public boolean add(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO salary (sid,eid,name, date, basicSalary, bouns, total) VALUES (?,?,?,?,?,?,?)", entity.getSId(), entity.getEId(), entity.getName(), entity.getDate(),entity.getBasicSalary(),entity.getBouns(),entity.getTotal());
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
    public Salary search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM salary WHERE sid=?",id+"");
        rst.next();
        return new Salary(id + "", rst.getString("eid"), rst.getString("name"),rst.getString("date"),rst.getDouble("basicSalary"),rst.getDouble("bouns"), rst.getDouble("total"));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM salary WHERE sid=?", id);
    }

    @Override
    public boolean update(Salary entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE salary SET  eid = ? , name = ?,date =?,basicSalary = ?,bouns =?,total=? WHERE sid = ?"  , entity.getEId(),entity.getName(),entity.getDate(),entity.getBasicSalary(),entity.getBouns(),entity.getTotal(),entity.getSId());
    }
}
