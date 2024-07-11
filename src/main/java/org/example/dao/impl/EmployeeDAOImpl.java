package org.example.dao.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.EmployeeDAO;
import org.example.dto.AtendenctDTO;
import org.example.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        while (rst.next()) {
           Employee employee = new Employee(rst.getString("eid"), rst.getString("name"), rst.getString("address"),rst.getString("contactNumber"),rst.getString("jobRole"),rst.getString("username"));
            System.out.println(employee+"dao impl");
            allEmployees.add(employee);

        }
        return allEmployees;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee (eid,name, address, contactNumber, jobRole, username) VALUES (?,?,?,?,?,?)", entity.getId(), entity.getName(), entity.getAddress(),entity.getContactNumber(),entity.getJobRole(),entity.getUsername());
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT eid FROM employee ORDER BY eid DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("eid");
            int newCustomerId = Integer.parseInt(id.replace("E00", "")) + 1;
            return String.format("E00", newCustomerId);
        } else {
            return "E001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

       return false;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();

        ResultSet rst = SQLUtil.execute("SELECT eid FROM employee");

        while(rst.next()) {
            idList.add(rst.getString(1));
        }
        return idList;
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT * from employee where eid =?", id+"");
        resultSet.next();
        return new Employee(id +"",resultSet.getString("name"),resultSet.getString("address"),resultSet.getString("contactNumber"),resultSet.getString("jobRole"),resultSet.getString("username"));
//
        }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE eid=?", id);
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET name = ?,address =?,contactNumber = ?,jobRole =?,username=? WHERE eid = ?"  , entity.getName(),entity.getAddress(),entity.getContactNumber(),entity.getJobRole(),entity.getUsername(),entity.getId());

    }

}
