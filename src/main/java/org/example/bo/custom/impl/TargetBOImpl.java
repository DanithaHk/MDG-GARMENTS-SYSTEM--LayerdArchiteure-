package org.example.bo.custom.impl;

import org.example.bo.custom.TargetBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ProductDAO;
import org.example.dao.custom.TargetDAO;
import org.example.dto.ProductDTO;
import org.example.dto.TargetDTO;
import org.example.entity.Product;
import org.example.entity.Target;

import java.sql.SQLException;
import java.util.ArrayList;

public class TargetBOImpl implements TargetBO {
    TargetDAO targetDAO = (TargetDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.TARGET);

    @Override
    public ArrayList<TargetDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<TargetDTO> targetDTOS = new ArrayList<>();
        ArrayList<Target> target = targetDAO.getAll();
        for(Target t : target) {
            TargetDTO targetDTO = new TargetDTO(t.getId(),t.getName(),t.getDate());
            targetDTOS.add(targetDTO);
        }

        return targetDTOS;

    }

    @Override
    public boolean addTarget(TargetDTO dto) throws SQLException, ClassNotFoundException {
        return targetDAO.add(new Target(dto.getId(), dto.getName(), dto.getDate()));

    }

}
