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
import org.example.bo.custom.EmployeeBO;
import org.example.dto.EmployeeDTO;
import org.example.util.ValidateUtil;
import org.example.view.tdm.EmployeeTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EmployeeController {
    @FXML
    private TableView<EmployeeTm> tableEmployee;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colJobrole;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUser;

    @FXML
    private AnchorPane rootEmployeeform;

    @FXML
    private TextField txtEUsername;

    @FXML
    private TextField txtEaddress;

    @FXML
    private TextField txtEcontactNumber;

    @FXML
    private TextField txtEid;

    @FXML
    private TextField txtEjobRole;

    @FXML
    private TextField txtEname;
    @FXML
    private JFXButton btnDelete;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    public void initialize(){
        setCellValueFactory();
        loadEmployeeTable();
        tableEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
        });
        hideButton();
    }

    private void hideButton() {
        btnDelete.setDisable(true);
    }

    private void setCellValueFactory() {
        tableEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tableEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        tableEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("JobRole"));
        tableEmployee.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("username"));

    }
    private void loadEmployeeTable() {
        tableEmployee.getItems().clear();
        /*Get all customers*/
        ArrayList<EmployeeDTO> allEmployee = null;
        try {
            allEmployee = employeeBO.getAllEmployee();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (EmployeeDTO e : allEmployee) {
            tableEmployee.getItems().add(new EmployeeTm(e.getId(), e.getName(), e.getAddress(),e.getContactNumber(),e.getJobRole(),e.getUsername()));
        }
    }


    public void txtAddProductIdOnAction(ActionEvent event) {
    }

    public void btnAddEmployeeOnAction(ActionEvent event) {
        String id = txtEid.getText();
        String name = txtEname.getText();
        String address = txtEaddress.getText();
        String contactNumber = txtEcontactNumber.getText();
        String jobRole = txtEjobRole.getText();
        String userName = txtEUsername.getText();

        boolean isValidate = validateEmployee();
        if (isValidate) {
            boolean isSaved = false;
            try {
                isSaved = employeeBO.addEmployee(new EmployeeDTO(id, name, address, contactNumber, jobRole, userName));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                    cleAr();
                    initialize();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void cleAr() {
        txtEid.clear();
        txtEname.clear();
        txtEaddress.clear();
        txtEcontactNumber.clear();
        txtEjobRole.clear();
        txtEUsername.clear();
    }

    public void btnSearchEmployeeOnAction(ActionEvent event) {
        String id = txtEid.getText();

        try {
            EmployeeDTO employeeDTO = employeeBO.searchEmployee(id);
            txtEname.setText(employeeDTO.getName());
            txtEaddress.setText(employeeDTO.getAddress());
            txtEcontactNumber.setText(employeeDTO.getContactNumber());
            txtEjobRole.setText(employeeDTO.getJobRole());
            txtEUsername.setText(employeeDTO.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnDeleteEmployeeOnAction(ActionEvent event)  {
        String id = tableEmployee.getSelectionModel().getSelectedItem().getId();
        try {
            boolean isDeleted = employeeBO.deleteEmployee(id);
            initialize();
            hideButton();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateEmployeeOnAction(ActionEvent event) {
        String id = txtEid.getText();
        String name = txtEname.getText();
        String address = txtEaddress.getText();
        String contactNumber = txtEcontactNumber.getText();
        String jobRole = txtEjobRole.getText();
        String userName = txtEUsername.getText();

        try {
            boolean isUpdate = employeeBO.updateEmployee(new EmployeeDTO(id,name,address,contactNumber,jobRole,userName));
           if (isUpdate) {
               new Exception("Updated!");
               cleAr();
               initialize();
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean validateEmployee() {
        String id = txtEid.getText();
        int num = 0;
        boolean isEmployeeIdValidated = Pattern.matches("[E][0-9]{3,}", id);
        if (!isEmployeeIdValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Id").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtEid);
        }
        String name = txtEname.getText();
        boolean isEmployeeNameValidated = Pattern.matches("[A-Z  a-z]{3,}", name);
        if (!isEmployeeNameValidated) {
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtEname);
        }
        String address = txtEaddress.getText();
        boolean isEmployeeAddressValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", address);
        if (!isEmployeeAddressValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Address").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtEaddress);
        }
        String contactNumber = txtEcontactNumber.getText();
        boolean isCustomerTelValidated = Pattern.matches("[0-9]{10}", contactNumber);
        if (!isCustomerTelValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Tel").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtEcontactNumber);
        }
        String jobRole = txtEjobRole.getText();
        boolean isEmployeejobroleValidated = Pattern.matches("[A-Z  a-z]{2,}", name);
        if (!isEmployeejobroleValidated) {
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtEjobRole);
        }

        String username = txtEUsername.getText();
        boolean isEusernameValidated = Pattern.matches("[A-Z  a-z]{2,}", name);
        if (!isEusernameValidated) {
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num = 1;
            ValidateUtil.vibrateTextField(txtEUsername);
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
