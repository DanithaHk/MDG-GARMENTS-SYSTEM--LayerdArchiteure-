package org.example.dao.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.TargetDetailDAO;
import org.example.entity.Salary;
import org.example.entity.TargetDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TargetDeatailDAOImpl implements TargetDetailDAO {
    @Override
    public ArrayList<TargetDetail> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<TargetDetail> allTargets = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM target_detail");
        while (rst.next()) {
            TargetDetail targetDetail = new TargetDetail(rst.getString("tid"), rst.getString("tname"),rst.getString("date"),rst.getString("targetCoverOrNot"),rst.getString("eid"));
            allTargets.add(targetDetail);
        }
        return allTargets ;
    }

    @Override
    public boolean add(TargetDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO target_detail (tid,tname, date, targetCoverOrNot, eid) VALUES (?,?,?,?,?)", entity.getId(),entity.getName(), entity.getDate(),entity.getTCoverOrNot(),entity.getEid());

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
    public TargetDetail search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM target_detail WHERE tid=?",id+"");
        rst.next();
        return new TargetDetail(id + "",  rst.getString("tname"),rst.getString("date"),rst.getString("targetCoverOrNot"),rst.getString("eid"));
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM target_detail WHERE tid=?", id);
    }

    @Override
    public boolean update(TargetDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE target_detail SET  tname = ?,date =?,targetCoverOrNot = ?,eid=? WHERE tid = ?"  , entity.getName(),entity.getDate(),entity.getTCoverOrNot(),entity.getEid(),entity.getId());
    }
}
