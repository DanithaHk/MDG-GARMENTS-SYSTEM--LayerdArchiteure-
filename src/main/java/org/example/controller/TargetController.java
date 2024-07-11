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
import org.example.bo.custom.TargetDetailBO;
import org.example.dto.SalaryDTO;
import org.example.dto.TargetDetailDTO;
import org.example.entity.Target;
import org.example.entity.TargetDetail;
import org.example.view.tdm.SalaryTm;
import org.example.view.tdm.TargetDetailTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class TargetController {
    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEid;

    @FXML
    private TableColumn<?, ?> colTragetCoverOrNot;

    @FXML
    private TableColumn<?, ?> colTragetName;

    @FXML
    private TableColumn<?, ?> colTrargetId;

    @FXML
    private AnchorPane rootTarget;

    @FXML
    private TableView<TargetDetailTm> tableTarget;

    @FXML
    private TextField txtTCoverOrNot;

    @FXML
    private TextField txtTEid;

    @FXML
    private TextField txtTdate;

    @FXML
    private TextField txtTid;

    @FXML
    private TextField txtTname;
    TargetDetailBO targetDetailBO = (TargetDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TARGETDETAIL);
    public void initialize() throws SQLException {

        setCellValueFactory();
        loadTargetDetailTable();
        setDate();

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtTdate.setText(String.valueOf(now));
    }

    private void loadTargetDetailTable() {
        tableTarget.getItems().clear();
        /*Get all customers*/
        ArrayList<TargetDetailDTO> allTarget = null;
        try {
            allTarget = targetDetailBO.getAllTarget();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (TargetDetailDTO t : allTarget) {
            tableTarget.getItems().add(new TargetDetailTm(t.getId(),t.getName(), t.getDate(), t.getTCoverOrNot(),t.getEid()));
        }
    }

    private void setCellValueFactory() {
        colTrargetId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTragetName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTragetCoverOrNot.setCellValueFactory(new PropertyValueFactory<>("tCoverOrNot"));
        colEid.setCellValueFactory(new PropertyValueFactory<>("eid"));
    }

    public void txtTidOnAction(ActionEvent event) {
        String id = txtTid.getText();

        try {
            Target target =targetDetailBO.getNames(id);
            txtTname.setText(target.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnSaveTargetOnAction(ActionEvent event) {
        String tid = txtTid.getText();
        String name = txtTname.getText();
        String date = txtTdate.getText();
        String tCoverOrNot = txtTCoverOrNot.getText();
        String eid = txtTEid.getText();

        try {
            boolean isSaved = targetDetailBO.saveTargetDeatil(new TargetDetailDTO(tid,name,date,tCoverOrNot,eid));
            initialize();
            new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnUpdateTargetOnAction(ActionEvent event) {
        String id = txtTid.getText();
        String name =txtTname.getText();
        String date = txtTdate.getText();
        String tCoverNot = txtTCoverOrNot.getText();
        String eid = txtTEid.getText();
        try {
            boolean isUpdate = targetDetailBO.updateTargetDetail(new TargetDetailDTO(id,name,date,tCoverNot,eid));
           if(isUpdate) {
               initialize();
               new Alert(Alert.AlertType.CONFIRMATION, "Update!").show();
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteTargetOnAction(ActionEvent event) {

    }

    public void btnSearchTargetOnAction(ActionEvent event) {
        String id = txtTid.getText();
        try {
            TargetDetailDTO targetDetailDTO = targetDetailBO.searchTarget(id);
            txtTname.setText(targetDetailDTO.getName());
            txtTdate.setText(targetDetailDTO.getDate());
            txtTCoverOrNot.setText(targetDetailDTO.getTCoverOrNot());
            txtTEid.setText(targetDetailDTO.getEid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
