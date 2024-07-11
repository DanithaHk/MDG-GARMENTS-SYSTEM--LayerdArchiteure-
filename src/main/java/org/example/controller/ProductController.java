package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.BOFactory;
import org.example.bo.custom.ProductBO;
import org.example.dto.ProductDTO;
import org.example.view.tdm.ProductTm;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    @FXML
    private TableColumn<?, ?> colCostPerOne;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private AnchorPane rootProductForm;

    @FXML
    private TableView<ProductTm> tableProduct;

    @FXML
    private TextField txtPCostPerOne;

    @FXML
    private TextField txtPDescription;

    @FXML
    private TextField txtPid;
    ProductBO productBO  = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);


    public void initialize() throws SQLException {
        setCellValueFactory();
        loadProductTable();
    }

    private void loadProductTable() {
        tableProduct.getItems().clear();
        ArrayList<ProductDTO> allProduct = null;
        try {
            allProduct = productBO.getAllProduct();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (ProductDTO p : allProduct) {
            tableProduct.getItems().add(new ProductTm(p.getId(), p.getDescription(), p.getCostPerOne()));
        }

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCostPerOne.setCellValueFactory(new PropertyValueFactory<>("costPerOne"));

    }

    public void btnAddProductOnAction(ActionEvent event) {
        String id = txtPid.getText();
        String desc = txtPDescription.getText();
        Double costPerOne = Double.valueOf(txtPCostPerOne.getText());

        boolean isSaved = false;
        try {
            isSaved = productBO.addProduct(new ProductDTO(id,desc,costPerOne));
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
            initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSeachOnAction() {
        String id = txtPid.getText();
        try {
            ProductDTO products = productBO.searchProduct(id);
            txtPDescription.setText(products.getDescription());
            txtPCostPerOne.setText(String.valueOf(products.getCostPerOne()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent event) {
        String id = txtPid.getText();
        String desc = txtPDescription.getText();
        Double costPerOne = Double.valueOf(txtPCostPerOne.getText());

        boolean isUpdate = false;
        try {
            isUpdate = productBO.update(new ProductDTO(id,desc,costPerOne));
            new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent event) {

        try {
            String id = tableProduct.getSelectionModel().getSelectedItem().getId();
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }

            //Delete Customer
            productBO.deleteProduct(id);

            tableProduct.getItems().remove(tableProduct.getSelectionModel().getSelectedItem());
            tableProduct.getSelectionModel().clearSelection();
            initialize();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " ).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        try{
//            String id = txtPid.getText();
//            boolean isDeleted = productBO.deleteProduct(id);
//            if (isDeleted){
//                new Exception("deleted ");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    private boolean existCustomer(String id) {
        try {
            return productBO.existProduct(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void enterONAction(javafx.scene.input.KeyEvent keyEvent) {
        btnSeachOnAction();
    }
}
