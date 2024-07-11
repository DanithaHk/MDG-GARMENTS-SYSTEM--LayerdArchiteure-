package org.example.bo.custom.impl;

import org.example.bo.custom.AddOrderBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ClientDAO;
import org.example.dao.custom.MaterialDAO;
import org.example.dao.custom.ProductDAO;
import org.example.dto.MaterialDTO;
import org.example.dto.ProductDTO;
import org.example.entity.Client;
import org.example.entity.Material;
import org.example.entity.Product;

import java.sql.SQLException;
import java.util.List;

public class AddOrderBOImpl implements AddOrderBO {
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.MATERIAL);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.PRODUCT);
    ClientDAO clientDAO = (ClientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.CLIENT);

    @Override
    public List<String> getMIds() throws SQLException, ClassNotFoundException {
        List<String> eIdList = materialDAO.getIds();
        return eIdList;
    }

    @Override
    public List<String> getPIds() throws SQLException, ClassNotFoundException {
        List<String> pIdList = productDAO.getIds();
        return pIdList;
    }

    @Override
    public Client searchByCNumber(String cNumber) throws SQLException, ClassNotFoundException {
        Client conatctList = clientDAO.getCustomerList(cNumber);
        return conatctList;
    }

    @Override
    public ProductDTO getProductDetails(String id) throws SQLException, ClassNotFoundException {
        Product product = productDAO.search(id);
        return (new ProductDTO(product.getId(),product.getDescription(),product.getCostPerOne()));
    }

    @Override
    public MaterialDTO getMaterialDeatils(String name) {
        Material material = null;
        try {
            material = materialDAO.materialsList(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return (new MaterialDTO(material.getId(),material.getDescription(),material.getQty(),material.getCostPerOne(),material.getUsername()));
    }
}
