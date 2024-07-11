package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;
    public boolean addProduct(ProductDTO dto) throws SQLException, ClassNotFoundException;
    public boolean existProduct(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteProduct(String id) throws SQLException, ClassNotFoundException;
    public boolean update(ProductDTO dto ) throws SQLException, ClassNotFoundException;

    ProductDTO searchProduct(String id) throws SQLException, ClassNotFoundException;
}
