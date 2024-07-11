package org.example.dao.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.TargetDAO;
import org.example.dto.AtendenctDTO;
import org.example.entity.Salary;
import org.example.entity.Target;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TargetDAOImpl implements TargetDAO {
    @Override
    public ArrayList<Target> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Target> allTargets = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM target");
        while (rst.next()) {
            Target target = new Target(rst.getString("tid"), rst.getString("tName"), rst.getString("date"));

            allTargets.add(target);

        }
        return allTargets ;
    }

    @Override
    public boolean add(Target entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO target (tid,tName,date) VALUES (?,?,?)", entity.getId(), entity.getName(), entity.getDate());
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
    public List<String> getIds() {
        return null;
    }

    @Override
    public Target search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM target WHERE tid=?",id+"");
        rst.next();
        return new Target(id + "", rst.getString("tname"),rst.getString("date"));
    }



    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Target entity) {
        return false;
    }
}
