package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    ArrayList<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException;
}
