package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.ProductDTO;
import org.example.dto.TargetDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TargetBO extends SuperBO {
    public ArrayList<TargetDTO> getAllProduct() throws SQLException, ClassNotFoundException;
    public boolean addTarget(TargetDTO dto) throws SQLException, ClassNotFoundException;
}
