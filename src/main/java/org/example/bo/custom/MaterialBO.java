package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.MaterialDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialBO extends SuperBO {
    ArrayList<MaterialDTO> loadMaterialTable() throws SQLException, ClassNotFoundException;

    boolean saveMaterial(MaterialDTO materialDTO) throws SQLException, ClassNotFoundException;

    boolean updateMaterial(MaterialDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteProduct(String id) throws SQLException, ClassNotFoundException;

    MaterialDTO searchMaterial(String id) throws SQLException, ClassNotFoundException;
}
