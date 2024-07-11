package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.BOFactory;
import org.example.bo.custom.ClientBO;
import org.example.bo.custom.EmployeeBO;
import org.example.dto.ClientDTO;
import org.example.dto.EmployeeDTO;
import org.example.entity.Client;
import org.example.view.tdm.ClientTm;
import org.example.view.tdm.EmployeeTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.example.util.ValidateUtil.vibrateTextField;

public class AddNewClientController {
        @FXML
        private TableView<ClientTm> tblClient;
        @FXML
        private TableColumn<?, ?> colAddress;

        @FXML
        private TableColumn<?, ?> colCnumber;

        @FXML
        private TableColumn<?, ?> colEmail;

        @FXML
        private TableColumn<?, ?> colId;

        @FXML
        private TableColumn<?, ?> colName;

        @FXML
        private AnchorPane rootaddNewClient;

        @FXML
        private TextField txtAddress;

        @FXML
        private TextField txtCId;

        @FXML
        private TextField txtCemail;

        @FXML
        private TextField txtCname;

        @FXML
        private TextField txtCnumber;
        ClientBO clientBO  = (ClientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CLIENT);

        public void initialize() throws SQLException {

            setCellValueFactory();
            loadClientTable();
        }

        private void setCellValueFactory() {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colCnumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        }

        private void loadClientTable() {
            tblClient.getItems().clear();
            /*Get all customers*/
            ArrayList<ClientDTO> allClient = null;
            try {
                allClient = clientBO.getClients();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            for (ClientDTO c : allClient) {
                tblClient.getItems().add(new ClientTm(c.getId(),c.getName(),c.getAddress(),c.getContactNumber(),c.getEmail()));
            }
        }


        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {

        }

        @FXML
        void btnClearOnACtion(ActionEvent event) {
            clear();
        }

        public void clear(){
            txtCId.clear();
            txtCname.clear();
            txtAddress.clear();
            txtCnumber.clear();
            txtCemail.clear();
        }

        @FXML
        void btnSaveAction(ActionEvent event) {
            String id = txtCId.getText();
            String name = txtCname.getText();
            String address = txtAddress.getText();
            String contactNumber = txtCnumber.getText();
            String email = txtCemail.getText();
            boolean isValidate = validateCustomer();
            if(isValidate) {
                boolean isSaved = false;
                try {
                    isSaved = clientBO.addClient(new ClientDTO(id, name, address, contactNumber, email));

                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Client saved!").show();
                        clear();
                        initialize();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);

                }
            }
        }

        public void txtKeyRelease(KeyEvent keyEvent) {
        }
    private boolean validateCustomer() {
        int num = 0;
        String id = txtCId.getText();

        boolean isCustomerIdValidated = Pattern.matches("[C][0-9]{3,}", id);
        if (!isCustomerIdValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Id").show();
            num = 1;
            vibrateTextField(txtCId);
        }

        String name = txtCname.getText();
        boolean isCustomerNameValidated = Pattern.matches("[A-Z  a-z]{3,}", name);
        if (!isCustomerNameValidated) {
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num = 1;
            vibrateTextField(txtCname);
        }


        String number = txtCnumber.getText();
        boolean isCustomerTelValidated = Pattern.matches("[0-9]{10}", number);
        if (!isCustomerTelValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Tel").show();
            num = 1;
            vibrateTextField(txtCnumber);
        }

        String email = txtCemail.getText();
        boolean isCustomerEmailValidated = Pattern.matches("[a-z].*(com|lk)", email);
        if (!isCustomerEmailValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Email").show();
            num = 1;
            vibrateTextField(txtCemail);
        }
        String address = txtAddress.getText();
        boolean isCustomerAddressValidated = Pattern.matches("[A-Za-z0-9/.,\\s]{3,}", address);
        if (!isCustomerAddressValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Address").show();
            num = 1;
            vibrateTextField(txtAddress);
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


