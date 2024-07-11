package org.example.dao;

import org.example.entity.Atendence;

import java.sql.SQLException;

public interface AttendenceNesessaryDAO extends SuperDAO{
    public Atendence searchByEId(String id) throws SQLException, ClassNotFoundException;
    public int getdateCount(String id) throws SQLException, ClassNotFoundException;
}
