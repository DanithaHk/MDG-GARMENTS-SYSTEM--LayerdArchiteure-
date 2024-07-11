package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.EmployeeDTO;
import org.example.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public  String generateNewCustomerID() throws SQLException, ClassNotFoundException;

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException;
    public List<String> getIds() throws SQLException, ClassNotFoundException;
    public Employee getnames(String id) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException;
}
