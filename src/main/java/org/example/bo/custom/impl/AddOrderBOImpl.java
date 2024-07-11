package org.example.bo.custom.impl;

import org.example.bo.custom.AddOrderBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.*;
import org.example.db.DbConnection;
import org.example.dto.*;
import org.example.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddOrderBOImpl implements AddOrderBO {
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.MATERIAL);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.PRODUCT);
    ClientDAO clientDAO = (ClientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.CLIENT);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.ORDER);
    OrderDeatilsDAO orderDeatilsDAO = (OrderDeatilsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.ORDERDEATAIL);
    MaterialDetailDAO materialDetailDAO = (MaterialDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.MATERIALDEATIL);

    @Override
    public List<String> getMNames() throws SQLException, ClassNotFoundException {
        List<String> eIdList = materialDAO.getNames();
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
            System.out.println(material);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return (new MaterialDTO(material.getId(),material.getDescription(),material.getQty(),material.getCostPerOne(),material.getUsername()));
    }

    @Override
    public boolean saveOrder(OrderDTO dto, MaterialDetailDTO materialDetailDTO) {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            boolean b1 = orderDAO.exist(dto.getOId());
            /*if order id already exist*/

            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);
            //Save the Order to the order table
            boolean b2 = orderDAO.add(new Order(dto.getOId(), dto.getName(), dto.getDate(), dto.getQty(), dto.getCid()));
            if (!b2) {

                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO d : dto.getOrderDetailDTOS()) {
                OrderDeatail orderDetails = new OrderDeatail(d.getOid(), d.getCid(), d.getCNumber(), d.getPid(),
                        d.getPName(), d.getUnitPrice(), d.getQty(), d.getDate(), d.getNededSwingCloth(), d.getTotal());
                boolean b3 = orderDeatilsDAO.add(orderDetails);
                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }


            //Save Material detail
            boolean b4 = materialDetailDAO.add(new MaterialDetail(materialDetailDTO.getOId(), materialDetailDTO.getDesc(), materialDetailDTO.getMId(), materialDetailDTO.getMaterialQty()));
            if (!b4) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private MaterialDetailDTO findMaterial(String nededSwingCloth) {
        try {
            MaterialDetail m = materialDetailDAO.findMaterial(nededSwingCloth);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    return null;
    }
}
