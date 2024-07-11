package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.AtendenceBO;
import org.example.bo.custom.BOFactory;
import org.example.bo.custom.EmployeeBO;
import org.example.dto.AtendenctDTO;
import org.example.dto.EmployeeDTO;
import org.example.view.tdm.AtendenceTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtendenceController {
    @FXML
    private ComboBox<String> cmbEmpolyeeId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPersentOrNot;

    @FXML
    private AnchorPane rootAttendence;

    @FXML
    private TableView<AtendenceTm> tableAttendence;

    @FXML
    private TextField txtAId;

    @FXML
    private TextField txtAName;


    static int presentOrNot = -1;

    AtendenceBO atendenceBO  = (AtendenceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATENDENCE);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);


    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAttendenceTable();
       // loadNextAtendenceId();
        getEmployeeId();
    }

    private String loadNextAtendenceId() {
        try {
            //Generate New ID
            return atendenceBO.generateNewAtendenceID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tableAttendence.getItems().isEmpty()) {
            return "A001";
        } else {
            String id = loadNextAtendenceId();
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("A00", newCustomerId);
        }
    }

    private void getEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> eIdList = atendenceBO.getIds();
            for (String id : eIdList) {
                obList.add(id);
        }

            cmbEmpolyeeId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAttendenceTable() {
        tableAttendence.getItems().clear();
        /*Get all customers*/
        ArrayList<AtendenctDTO> allAtenedence = null;
        try {
            allAtenedence = atendenceBO.getAllAtendence();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (AtendenctDTO a : allAtenedence) {
            tableAttendence.getItems().add(new AtendenceTm(a.getId(), a.getName(), a.getDate(),a.getPresentOrNot(),a.getEmployeeid()));
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPersentOrNot.setCellValueFactory(new PropertyValueFactory<>("presentOrNot"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeid"));
    }

    public void txtIdOnAction(ActionEvent event) {
        String id = txtAId.getText();
        EmployeeDTO employeeDTO = null;
        try {
            List<String> ids  = employeeBO.getIds();
            if (employeeDTO != null) {
                txtAName.setText(employeeDTO.getName());
                cmbEmpolyeeId.setValue(employeeDTO.getId());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent event) {
    }

    public void btnPresntOnAction(ActionEvent event) {
        presentOrNot = 1;
        btnSaveOnAction();
    }

    private void btnSaveOnAction() {
        String id = txtAId.getText();
        String name = txtAName.getText();
        String now = String.valueOf(LocalDate.now());
        String date = String.valueOf(now);

        String employeeId = cmbEmpolyeeId.getValue();
        boolean isSaved = false;
        try {
        isSaved = atendenceBO.saveAtendence(new AtendenctDTO(id,name,now,presentOrNot,employeeId));
        new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
            clear();
            initialize();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAbsantOnAction(ActionEvent event) {
        presentOrNot = 0;
        btnSaveOnAction();
    }
    @FXML
    void cmbEmpolyeIdOnAction(ActionEvent event) {
        String eid = cmbEmpolyeeId.getValue();
        try {
            String getnames = String.valueOf(atendenceBO.getnames(eid).getName());
            if (getnames != null) {
                txtAName.setText(getnames);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void clear(){
        txtAId.clear();
        txtAName.clear();

    }

}
