package org.example.bo.custom.impl;

import org.example.bo.custom.ProductBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.EmployeeDAO;
import org.example.dao.custom.ProductDAO;
import org.example.dto.EmployeeDTO;
import org.example.dto.ProductDTO;
import org.example.entity.Employee;
import org.example.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.PRODUCT);

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
       ArrayList<ProductDTO> productDTOS = new ArrayList<>();
       ArrayList<Product> product = productDAO.getAll();
        for(Product p : product) {
            ProductDTO productDTO = new ProductDTO(p.getId(),p.getDescription(),p.getCostPerOne());
            productDTOS.add(productDTO);
        }

        return productDTOS;
    }

    @Override
    public boolean addProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.add(new Product(dto.getId(), dto.getDescription(), dto.getCostPerOne()));

    }

    @Override
    public boolean existProduct(String id) throws SQLException, ClassNotFoundException {
        return productDAO.exist(id);
    }

    @Override
    public boolean deleteProduct(String id) throws SQLException, ClassNotFoundException {
        return productDAO.delete(id);
    }

    @Override
    public boolean update(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(dto.getId(),dto.getDescription(),dto.getCostPerOne()));
    }

    @Override
    public ProductDTO searchProduct(String id) throws SQLException, ClassNotFoundException {
            Product product = productDAO.search(id);
        return new ProductDTO(product.getId(), product.getDescription(), product.getCostPerOne());
    }
}
