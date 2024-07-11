package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.BOFactory;
import org.example.bo.custom.OrderBO;
import org.example.dao.custom.OrderDAO;
import org.example.dto.EmployeeDTO;
import org.example.dto.OrderDTO;
import org.example.dto.OrdersDTO;
import org.example.view.tdm.EmployeeTm;
import org.example.view.tdm.OrderTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    @FXML
    private TableColumn<?, ?> colCid;

    @FXML
    private TableColumn<?, ?> colOContactNumber;

    @FXML
    private TableColumn<?, ?> colOdate;

    @FXML
    private TableColumn<?, ?> colOid;

    @FXML
    private TableColumn<?, ?> colOqty;

    @FXML
    private TableColumn<?, ?> colPid;

    @FXML
    private TableColumn<?, ?> colPname;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<OrderTm> orderTable;
    @FXML
    private AnchorPane rootOrderFrom;

    @FXML
    private TextField txtOclientId;

    @FXML
    private TextField txtOdate;

    @FXML
    private TextField txtOid;

    @FXML
    private TextField txtOname;

    @FXML
    private TextField txtOqty;
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    public void initialize() {
        
        loadOrderTable();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colOid.setCellValueFactory(new PropertyValueFactory<>("oId"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("cId"));
        colPid.setCellValueFactory(new PropertyValueFactory<>("pId"));
        colPname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOdate.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    private void loadOrderTable() {
        orderTable.getItems().clear();
        /*Get all customers*/
        ArrayList<OrdersDTO> allOrders = null;
        try {
            allOrders = orderBO.getAllOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (OrdersDTO o : allOrders) {
            orderTable.getItems().add(new OrderTm(o.getOId(), o.getName(), o.getDate(),o.getQty(),o.getCid()));
        }
        
    }

}
