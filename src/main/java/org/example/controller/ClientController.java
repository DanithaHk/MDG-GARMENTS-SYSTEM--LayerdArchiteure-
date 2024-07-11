package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.BOFactory;
import org.example.bo.custom.ClientBO;
import org.example.dto.ClientDTO;
import org.example.view.tdm.ClientTm;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.example.util.ValidateUtil.vibrateTextField;

public class ClientController {
    @FXML
    private TableView<ClientTm> tblClient;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private AnchorPane rootClientpage;

    @FXML
    private TextField txtCadress;

    @FXML
    private TextField txtCconatctnumber;

    @FXML
    private TextField txtCemail;

    @FXML
    private TextField txtCname;

    @FXML
    private TextField txtclientId;
    @FXML
    private JFXButton btnDelete;
    ClientBO clientBO = (ClientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CLIENT);


    public void initialize() {
        setCellValueFactory();
        loadClientTable();
        tblClient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
        });
        hideButton();

    }

    private void hideButton() {
        btnDelete.setDisable(true);
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
            tblClient.getItems().add(new ClientTm(c.getId(), c.getName(), c.getAddress(), c.getContactNumber(), c.getEmail()));
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    public void txtAddClientIdOnAction(ActionEvent event) {
        btnSearchClientOnAction();
    }

    public void btnAddClientOnAction(ActionEvent event) {

        String id = txtclientId.getText();
        String name = txtCname.getText();
        String address = txtCadress.getText();
        String contactNumber = txtCconatctnumber.getText();
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

    public void btnDeleteClientOnAction(ActionEvent event) {
        String id = tblClient.getSelectionModel().getSelectedItem().getId();
        try {
            //Delete Customer
            clientBO.deleteClient(id);

            tblClient.getItems().remove(tblClient.getSelectionModel().getSelectedItem());
            tblClient.getSelectionModel().clearSelection();
            hideButton();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSearchClientOnAction() {
        String id = txtclientId.getText();
        try {
            ClientDTO client = clientBO.searchClient(id);
            txtCname.setText(client.getName());
            txtCadress.setText(client.getAddress());
            txtCconatctnumber.setText(client.getContactNumber());
            txtCemail.setText(client.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateClientOnAction(ActionEvent event) {
        String id = txtclientId.getText();
        String name = txtCname.getText();
        String address = txtCadress.getText();
        String contactNumber = txtCconatctnumber.getText();
        String email = txtCemail.getText();
        boolean isUpdated = false;

            try {
                isUpdated = clientBO.updateClient(new ClientDTO(id, name, address, contactNumber, email));
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                initialize();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

    }

    public void clear() {
        txtCadress.clear();
        txtCname.clear();
        txtCconatctnumber.clear();
        txtclientId.clear();
        txtCemail.clear();
    }

    private boolean validateCustomer() {
        int num = 0;
        String id = txtclientId.getText();

        boolean isCustomerIdValidated = Pattern.matches("[C][0-9]{3,}", id);
        if (!isCustomerIdValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Id").show();
            num = 1;
            vibrateTextField(txtclientId);
        }

        String name = txtCname.getText();
        boolean isCustomerNameValidated = Pattern.matches("[A-Z  a-z]{3,}", name);
        if (!isCustomerNameValidated) {
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num = 1;
            vibrateTextField(txtCname);
        }


        String number = txtCconatctnumber.getText();
        boolean isCustomerTelValidated = Pattern.matches("[0-9]{10}", number);
        if (!isCustomerTelValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Tel").show();
            num = 1;
            vibrateTextField(txtCconatctnumber);
        }

        String email = txtCemail.getText();
        boolean isCustomerEmailValidated = Pattern.matches("[a-z].*(com|lk)", email);
        if (!isCustomerEmailValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Email").show();
            num = 1;
            vibrateTextField(txtCemail);
        }
        String address = txtCadress.getText();
        boolean isCustomerAddressValidated = Pattern.matches("[A-Za-z0-9/.,\\s]{3,}", address);
        if (!isCustomerAddressValidated) {
            //new Alert(Alert.AlertType.ERROR, "INVALID Address").show();
            num = 1;
            vibrateTextField(txtCadress);
        }
        if (num == 1) {
            num = 0;
            return false;
        } else {
            num = 0;
            return true;
        }
    }


    public void txtCustomerIDOnKeyReleased(KeyEvent keyEvent) {
        //  Regex.setTextColor(lk.ijse.AutoCareCenter.Util.TextField.ID, (JFXTextField) txtId);
    }

    public void txtCustomerNameOnKeyReleased(KeyEvent keyEvent) {

    }

    public void txtCustomerAddressOnKeyReleased(KeyEvent keyEvent) {
    }

    public void txtCustomerContactOnKeyReleased(KeyEvent keyEvent) {
    }

    public void txtCustomerNameOnKeyReleased(ActionEvent event) {


    }

    public void txtCustomerAddressOnKeyReleased(ActionEvent event) {


    }

    public void txtCustomerContactOnKeyReleased(ActionEvent event) {


    }

    public void txtCustomerEmailOnKeyReleased(ActionEvent event) {

    }
}
