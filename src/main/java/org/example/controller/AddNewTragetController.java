package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.BOFactory;
import org.example.bo.custom.ProductBO;
import org.example.bo.custom.TargetBO;
import org.example.dto.ProductDTO;
import org.example.dto.TargetDTO;
import org.example.entity.Target;
import org.example.view.tdm.ProductTm;
import org.example.view.tdm.TragetTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddNewTragetController {
    @FXML
    private TableView<TragetTm> TragetTable;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane rootaddNewTarget;

    @FXML
    private TextField txtTdate;

    @FXML
    private TextField txtTid;

    @FXML
    private TextField txtTname;
    TargetBO targetBO  = (TargetBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TARGET);


    public void initialize() throws SQLException {
        setCellValueFactory();
        loadClientTable();
        setDate();
    }



    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    private void loadClientTable() {
        TragetTable.getItems().clear();
        ArrayList<TargetDTO> allTarget = null;
        try {
            allTarget = targetBO.getAllProduct();
            System.out.println(allTarget);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (TargetDTO t : allTarget) {
            TragetTable.getItems().add(new TragetTm(t.getId(), t.getName(), t.getDate()));

        }
    }

    public void btnSaveTargetOnAction(ActionEvent event) {
        String id = txtTid.getText();
        String name = txtTname.getText();
        String date = txtTdate.getText();
        boolean isSaved = false;
        try {
            isSaved = targetBO.addTarget(new TargetDTO(id,name,date));
            initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnClearTargetOnAction(ActionEvent event) {
        clear();
    }
    public void clear () {
        txtTdate.clear();
        txtTid.clear();
        txtTname.clear();

    }
    private void setDate() {
        LocalDate now = LocalDate.now();
        txtTdate.setText(String.valueOf(now));
    }
}
