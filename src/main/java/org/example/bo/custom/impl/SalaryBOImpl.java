package org.example.bo.custom.impl;

import org.example.bo.custom.SalaryBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AtendenceDAO;
import org.example.dao.custom.EmployeeDAO;
import org.example.dao.custom.SalaryDAO;
import org.example.dto.SalaryDTO;
import org.example.entity.Atendence;
import org.example.entity.Employee;
import org.example.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.SALARY);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.EMPLOYEE);
   AtendenceDAO atendenceDAO = (AtendenceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.ATENDENCE);
    @Override
    public boolean saveSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.add(new Salary(dto.getSId(), dto.getEId(), dto.getName(), dto.getDate(), dto.getBasicSalary(),dto.getBouns(),dto.getTotal()));
    }

    @Override
    public ArrayList<SalaryDTO> getAllSalary() throws SQLException, ClassNotFoundException {
        ArrayList <SalaryDTO> salayDTOS = new ArrayList<>();
        ArrayList <Salary> salaries = salaryDAO.getAll();
        for(Salary s : salaries) {
            SalaryDTO salaryDTO = new SalaryDTO(s.getSId(),s.getEId(),s.getName(),s.getDate(),s.getBasicSalary(),s.getBouns(),s.getTotal());
            salayDTOS.add(salaryDTO);
        }

        return salayDTOS;    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> eIdList = employeeDAO.getIds();
        return eIdList;
    }

    @Override
    public Employee getnames(String eid) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(eid);
    }

    @Override
    public Atendence searchByEId(String id) throws SQLException, ClassNotFoundException {
        return  atendenceDAO.searchByEId(id);
    }

    @Override
    public int getdateCount(String id) throws SQLException, ClassNotFoundException {

        return atendenceDAO.getdateCount(id);
    }

    @Override
    public boolean updateSalary(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(salaryDTO.getSId(), salaryDTO.getEId(), salaryDTO.getName(), salaryDTO.getDate(), salaryDTO.getBasicSalary(), salaryDTO.getBouns(), salaryDTO.getTotal()));
    }

    @Override
    public SalaryDTO searchSalary(String id) throws SQLException, ClassNotFoundException {
        Salary salary = salaryDAO.search(id);
        return (new SalaryDTO(salary.getSId(),salary.getEId(),salary.getName(),salary.getDate(),salary.getBasicSalary(),salary.getBouns(),salary.getTotal()));

    }
}
