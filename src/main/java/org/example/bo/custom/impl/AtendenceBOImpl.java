package org.example.bo.custom.impl;

import org.example.bo.custom.AtendenceBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AtendenceDAO;
import org.example.dao.custom.EmployeeDAO;
import org.example.dto.AtendenctDTO;
import org.example.entity.Atendence;
import org.example.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtendenceBOImpl implements AtendenceBO {
    AtendenceDAO atendenceDAO = (AtendenceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.ATENDENCE);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.EMPLOYEE);
    @Override
    public ArrayList<AtendenctDTO> getAllAtendence() throws SQLException, ClassNotFoundException {
        ArrayList <AtendenctDTO> atendenctDTOS = new ArrayList<>();
        ArrayList <Atendence> atendence = atendenceDAO.getAll();
        for(Atendence a : atendence) {
            AtendenctDTO atendenctDTO = new AtendenctDTO(a.getId(),a.getName(),a.getDate(),a.getPresentOrNot(),a.getEmployeeid());
            atendenctDTOS.add(atendenctDTO);
        }

        return atendenctDTOS;
    }

    @Override
    public boolean saveAtendence(AtendenctDTO dto) throws SQLException, ClassNotFoundException {
       return atendenceDAO.add(new Atendence(dto.getId(),dto.getName(),dto.getDate(),dto.getPresentOrNot(), dto.getEmployeeid()));
    }

    @Override
    public String generateNewAtendenceID() throws SQLException, ClassNotFoundException {
        return atendenceDAO.generateNewID();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> eIdList = employeeDAO.getIds();
        return eIdList;
    }

    @Override
    public Employee getnames(String eid) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(eid);
    }


}
