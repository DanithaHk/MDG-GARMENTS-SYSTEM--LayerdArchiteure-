package org.example.bo.custom.impl;

import org.example.bo.custom.EmployeeBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.EmployeeDAO;
import org.example.dto.EmployeeDTO;
import org.example.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.EMPLOYEE);



    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList <EmployeeDTO> employeeDTOS = new ArrayList<>();
        ArrayList <Employee> employee = employeeDAO.getAll();
        for(Employee e : employee) {
            EmployeeDTO employeeDTO = new EmployeeDTO(e.getId(),e.getName(),e.getAddress(),e.getContactNumber(),e.getJobRole(),e.getUsername());
           employeeDTOS.add(employeeDTO);
        }

        return employeeDTOS;
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {

        return employeeDAO.add(new Employee(dto.getId(), dto.getName(), dto.getAddress(),dto.getContactNumber(), dto.getJobRole(), dto.getUsername()));
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
       return  employeeDAO.exist(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return employeeDAO.getIds();
    }

    @Override
    public Employee getnames(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(id);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getContactNumber(),employeeDTO.getJobRole(),employeeDTO.getUsername()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee e = employeeDAO.search(id);
        return new EmployeeDTO(e.getId(),e.getName(),e.getAddress(),e.getContactNumber(),e.getJobRole(),e.getUsername());
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }
}
