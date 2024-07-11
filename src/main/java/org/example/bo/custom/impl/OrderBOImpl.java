package org.example.bo.custom.impl;

import org.example.bo.custom.OrderBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.OrderDAO;
import org.example.dto.OrderDTO;
import org.example.dto.OrdersDTO;
import org.example.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.ORDER);
    @Override
    public ArrayList<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList <OrdersDTO> orderDTOS = new ArrayList<>();
        ArrayList <Order> orders = orderDAO.getAll();
        for(Order o : orders) {
            OrdersDTO orderDTO = new OrdersDTO(o.getOId(),o.getName(),o.getDate(),o.getQty(),o.getCid());
            orderDTOS.add(orderDTO);
        }

        return orderDTOS;
    }
}
