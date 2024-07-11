package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.MaterialDTO;
import org.example.dto.ProductDTO;
import org.example.entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface AddOrderBO extends SuperBO {
    List<String> getMIds() throws SQLException, ClassNotFoundException;

    List<String> getPIds() throws SQLException, ClassNotFoundException;

    Client searchByCNumber(String cNumber) throws SQLException, ClassNotFoundException;

    ProductDTO getProductDetails(String id) throws SQLException, ClassNotFoundException;

    MaterialDTO getMaterialDeatils(String name);
}
