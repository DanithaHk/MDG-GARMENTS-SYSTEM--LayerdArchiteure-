package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.BOFactory;
import org.example.bo.custom.EmployeeBO;
import org.example.bo.custom.ProductBO;
import org.example.dto.EmployeeDTO;
import org.example.dto.ProductDTO;
import org.example.view.tdm.EmployeeTm;
import org.example.view.tdm.ProductTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddNewProductController {
    @FXML
    private AnchorPane rootaddNewProduct;
    @FXML
    private TableView<ProductTm> productTable;

    @FXML
    private TableColumn<?, ?> colPcostPerUit;

    @FXML
    private TableColumn<?, ?> colPdesc;

    @FXML
    private TableColumn<?, ?> colPid;


    @FXML
    private TextField txtCostPerUnit;

    @FXML
    private TextField txtPdescription;

    @FXML
    private TextField txtPid;
    ProductBO productBO  = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);


    public void initialize() throws SQLException {

        setCellValueFactory();
        loadProductTable();
    }



    private void setCellValueFactory() {
        productTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        productTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        productTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("costPerOne"));

    }

    private void loadProductTable() {
        productTable.getItems().clear();
        ArrayList<ProductDTO> allProduct = null;
        try {
            allProduct = productBO.getAllProduct();
            System.out.println(allProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (ProductDTO p : allProduct) {
            productTable.getItems().add(new ProductTm(p.getId(), p.getDescription(), p.getCostPerOne()));
        }

    }
    public void clear () {
        txtPid.clear();
        txtPdescription.clear();
        txtCostPerUnit.clear();
    }

    public void btnAddProductOnAction(ActionEvent event) {
        String id = txtPid.getText();
        String desc = txtPdescription.getText();
        Double costPerOne = Double.valueOf(txtCostPerUnit.getText());

        boolean isSaved = false;
        try {
            isSaved = productBO.addProduct(new ProductDTO(id,desc,costPerOne));
            initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClearOnAction(ActionEvent event) {
    }

    public void txtAddProductIdOnAction(ActionEvent event) {
    }
}