package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.BOFactory;
import org.example.bo.custom.MaterialBO;
import org.example.dto.MaterialDTO;
import org.example.util.ValidateUtil;
import org.example.view.tdm.MaterialTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MaterialController {

    @FXML
    private TableColumn<?, ?> colCostPErUnit;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<MaterialTm> materialtable;

    @FXML
    private AnchorPane rootMaterial;

    @FXML
    private TextField txtMCostPerUnit;
    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtMdescription;

    @FXML
    private TextField txtMid;

    @FXML
    private TextField txtMqty;
    @FXML
    private JFXButton btnDelete;
    MaterialBO materialBO = (MaterialBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MATERIAL);
    public  void initialize(){
        loadMaterialTable();
        setCellValueFactory();
        materialtable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
        });
        hideButton();

    }

    private void hideButton() {
        btnDelete.setDisable(true);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCostPErUnit.setCellValueFactory(new PropertyValueFactory<>("costPerOne"));
    }

    private void loadMaterialTable() {
    materialtable.getItems().clear();
        ArrayList<MaterialDTO> allMaterials = null;
        try {
            allMaterials = materialBO.loadMaterialTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (MaterialDTO m : allMaterials) {
            materialtable.getItems().add(new MaterialTm(m.getId(), m.getDescription(),m.getQty(),m.getCostPerOne(),m.getUsername()));
        }
    }

    public void txtAddMaterialIdOnAction(ActionEvent event) {
    }

    public void btnAddMaterialtOnAction(ActionEvent event) {
        boolean isValied = validateMaterial();
        if (isValied) {
            String id = txtMid.getText();
            String desc = txtMdescription.getText();
            double cost = Double.parseDouble(txtMCostPerUnit.getText());
            int qty = Integer.parseInt(txtMqty.getText());
            String user = txtUsername.getText();
            try {
                boolean isSaved = materialBO.saveMaterial(new MaterialDTO(id, desc, qty, cost, user));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                    clear();
                    initialize();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void btnSearchMaterialOnAction() {
        String id = txtMid.getText();

            try {
                MaterialDTO materialDTO = materialBO.searchMaterial(id);
                txtMdescription.setText(materialDTO.getDescription());
                txtMqty.setText(String.valueOf(materialDTO.getQty()));
                txtMCostPerUnit.setText(String.valueOf(materialDTO.getCostPerOne()));
                txtUsername.setText(materialDTO.getUsername());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

        }
    }

    public void btnUpdateMaterialOnAction(ActionEvent event) {
            String id = txtMid.getText();
            String desc = txtMdescription.getText();
            int qty = Integer.parseInt(txtMqty.getText());
            double cost = Double.parseDouble(txtMCostPerUnit.getText());
            String user = txtUsername.getText();

        try {
            boolean isUpdate = materialBO.updateMaterial(new MaterialDTO(id, desc, qty,cost,user));
            if (isUpdate) {
                new Exception("Updated!");
                clear();
                initialize();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteMaterialOnAction(ActionEvent event) {
        try {
            String id = materialtable.getSelectionModel().getSelectedItem().getId();
            //Delete Customer
            materialBO.deleteProduct(id);

            materialtable.getItems().remove(materialtable.getSelectionModel().getSelectedItem());
            materialtable.getSelectionModel().clearSelection();
            initialize();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " ).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        txtMqty.clear();
        txtMdescription.clear();
        txtMqty.clear();
        txtMCostPerUnit.clear();
        txtUsername.clear();
    }

    public void txtMidPressed() {
        btnSearchMaterialOnAction();
    }
    private boolean validateMaterial() {
        int num =0;
        String id = txtMid.getText();
        boolean isMaterialIdValidated = Pattern.matches("[M][0-9]{3,}", id);
        if (!isMaterialIdValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Id").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtMid);
        }
        String desc = txtMdescription.getText();
        boolean isProductNameValidated = Pattern.matches("[A-Z  a-z]{3,}", desc);
        if (!isProductNameValidated) {
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtMdescription);
        }
        String qty = String.valueOf(Integer.parseInt(txtMqty.getText()));
        boolean isMaterialQtyValidated = Pattern.matches("[0-9 ]{3,}", qty);
        if (!isMaterialQtyValidated) {
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtMqty);
        }
        String costPUnit = String.valueOf(Double.valueOf(txtMCostPerUnit.getText()));
        boolean isMaterialCostPerunitValidated = Pattern.matches("[0-9 .]{3,}", costPUnit);
        if (!isMaterialCostPerunitValidated) {
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtMCostPerUnit);
        }

        String username = txtUsername.getText();
        boolean isUsernameValidated = Pattern.matches("[A-Z  a-z]{3,}", username);
        if (!isUsernameValidated) {
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtUsername);
        }
        if (num == 1) {
            num = 0;
            return false;
        } else {
            num = 0;
            return true;
        }
    }
}
