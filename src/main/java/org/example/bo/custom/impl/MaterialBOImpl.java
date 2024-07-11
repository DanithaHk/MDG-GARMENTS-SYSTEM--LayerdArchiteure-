package org.example.bo.custom.impl;

import org.example.bo.custom.MaterialBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.MaterialDAO;
import org.example.dto.MaterialDTO;
import org.example.entity.Material;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialBOImpl implements MaterialBO {
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.MATERIAL);
    @Override
    public ArrayList<MaterialDTO> loadMaterialTable() throws SQLException, ClassNotFoundException {
        ArrayList<MaterialDTO> materialDTOS = new ArrayList<>();
        ArrayList<Material> material = materialDAO.getAll();
        for(Material m : material) {
            MaterialDTO materialDTO = new MaterialDTO(m.getId(),m.getDescription(),m.getQty(),m.getCostPerOne(),m.getUsername());
            materialDTOS.add(materialDTO);
        }

        return materialDTOS;
    }

    @Override
    public boolean saveMaterial(MaterialDTO materialDTO) throws SQLException, ClassNotFoundException {
       return materialDAO.add(new Material(materialDTO.getId(),materialDTO.getDescription(),materialDTO.getQty(),materialDTO.getCostPerOne(),materialDTO.getUsername()));
    }

    @Override
    public boolean updateMaterial(MaterialDTO dto) throws SQLException, ClassNotFoundException {
        return materialDAO.update(new Material(dto.getId(), dto.getDescription(), dto.getQty(), dto.getCostPerOne(), dto.getUsername()));
    }

    @Override
    public boolean deleteProduct(String id) throws SQLException, ClassNotFoundException {
        return materialDAO.delete(id);
    }

    @Override
    public MaterialDTO searchMaterial(String id) throws SQLException, ClassNotFoundException {
        Material m = materialDAO.search(id);
        return new MaterialDTO(m.getId(),m.getDescription(),m.getQty(),m.getCostPerOne(), m.getUsername());
    }

}
