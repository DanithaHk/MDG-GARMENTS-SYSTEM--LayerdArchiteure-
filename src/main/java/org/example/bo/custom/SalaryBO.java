package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.SalaryDTO;
import org.example.entity.Atendence;
import org.example.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SalaryBO extends SuperBO {

    boolean saveSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException;

    ArrayList<SalaryDTO> getAllSalary() throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;

    Employee getnames(String eid) throws SQLException, ClassNotFoundException;

    Atendence searchByEId(String id) throws SQLException, ClassNotFoundException;

    int getdateCount(String id) throws SQLException, ClassNotFoundException;

    boolean updateSalary(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException;

    SalaryDTO searchSalary(String id) throws SQLException, ClassNotFoundException;
}
