package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.AtendenctDTO;
import org.example.entity.Employee;

import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

public interface AtendenceBO extends SuperBO {
    public ArrayList<AtendenctDTO> getAllAtendence() throws SQLException, ClassNotFoundException;
    public boolean saveAtendence(AtendenctDTO atendenctDTO) throws SQLException, ClassNotFoundException;
    public String generateNewAtendenceID() throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;

    Employee getnames(String eid) throws SQLException, ClassNotFoundException;
}
