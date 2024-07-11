package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.BOFactory;
import org.example.bo.custom.SalaryBO;
import org.example.dto.EmployeeDTO;
import org.example.dto.SalaryDTO;
import org.example.entity.Atendence;
import org.example.view.tdm.EmployeeTm;
import org.example.view.tdm.SalaryTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalaryController {
    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colBasicSalary;

    @FXML
    private TableColumn<?, ?> colBonus;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEid;
    @FXML
    private AnchorPane rootSalary;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colSId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableView<SalaryTm> tblsalary;

    @FXML
    private TextField txtBasicSalary;

    @FXML
    private TextField txtBouns;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtSid;
    SalaryBO salaryBO = (SalaryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SALARY);
    public void initialize(){
        setCellValueFactory();
        loadSalaryTable();
        loadEmpoyeeIds();
        setDate();
    }

    private void loadEmpoyeeIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> eIdList = salaryBO.getIds();
            for (String id : eIdList) {
                obList.add(id);
            }

            cmbEmployeeId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colSId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        colEid.setCellValueFactory(new PropertyValueFactory<>("eId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colBasicSalary.setCellValueFactory(new PropertyValueFactory<>("basicSalary"));
        colBonus.setCellValueFactory(new PropertyValueFactory<>("bouns"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
    private void loadSalaryTable() {
        tblsalary.getItems().clear();
        /*Get all customers*/
        ArrayList<SalaryDTO> allSalary = null;
        try {
            allSalary = salaryBO.getAllSalary();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (SalaryDTO s : allSalary) {
            tblsalary.getItems().add(new SalaryTm(s.getSId(),s.getEId(), s.getName(), s.getDate(),s.getBasicSalary(),s.getBouns(),s.getTotal()));
        }

    }



    public void cmbEmpolyeeIdOnAction(ActionEvent event) {
        String eid = cmbEmployeeId.getValue();
        try {
            String getnames = String.valueOf(salaryBO.getnames(eid).getName());
            if (getnames != null) {
                setBouns();
                txtEmployeeName.setText(getnames);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnSaveOnAction(ActionEvent event) {
        String sId = txtSid.getText();
        String eId = cmbEmployeeId.getValue();
        String name = txtDate.getText();
        String date = txtDate.getText();
        double basicSalary = Double.parseDouble(txtBasicSalary.getText());
        double bouns = Double.parseDouble(txtBouns.getText());
        double total = basicSalary + bouns;
        try {
            boolean isSaved = salaryBO.saveSalary(new SalaryDTO(sId,eId,name,date,basicSalary,bouns,total));
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
                initialize();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnUpdateOnAction(ActionEvent event) {
        String sId = txtSid.getText();
        String eId = cmbEmployeeId.getValue();
        String name = txtDate.getText();
        String date = txtDate.getText();
        double basicSalary = Double.parseDouble(txtBasicSalary.getText());
        double bouns = Double.parseDouble(txtBouns.getText());
        double total = basicSalary + bouns;

        boolean isUpdate = false;
        try {
            isUpdate = salaryBO.updateSalary(new SalaryDTO(sId,eId,name,date,basicSalary,bouns,total));
            if(isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Update!").show();
                initialize();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void txtSidPressed( ) {
        String id = txtSid.getText();
        try {
            SalaryDTO salaryDTO = salaryBO.searchSalary(id);
            txtDate.setText(salaryDTO.getDate());
            txtEmployeeName.setText(salaryDTO.getName());
            txtBouns.setText(String.valueOf(salaryDTO.getBouns()));
            txtBasicSalary.setText(String.valueOf(salaryDTO.getBasicSalary()));
            cmbEmployeeId.setValue(salaryDTO.getEId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }
    private void setBouns() {
        String id = cmbEmployeeId.getValue();
        try {
            Atendence attendence = salaryBO.searchByEId(id);
            if (attendence != null) {
                int dateCount = salaryBO.getdateCount(id);
                if (dateCount >= 2) {
                    txtBouns.setText(String.valueOf(5000));
                }
                if (dateCount < 2) {
                    txtBouns.setText("0");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
