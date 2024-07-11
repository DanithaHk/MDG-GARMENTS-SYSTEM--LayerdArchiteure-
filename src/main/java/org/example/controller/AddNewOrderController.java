package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.AddOrderBO;
import org.example.bo.custom.BOFactory;
import org.example.dto.*;
import org.example.entity.Client;
import org.example.view.tdm.CartTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AddNewOrderController {
    @FXML
    private JFXComboBox<String> cmbProductId;
    @FXML
    private JFXComboBox<String> cmbMaterialName;


    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCid;

    @FXML
    private TableColumn<?, ?> colOContactNumber;

    @FXML
    private TableColumn<?, ?> colMaterialQty;

    @FXML
    private TableColumn<?, ?> colOdate;

    @FXML
    private TableColumn<?, ?> colOid;

    @FXML
    private TableColumn<?, ?> colOqty;

    @FXML
    private TableColumn<?, ?> colPid;

    @FXML
    private TableColumn<?, ?> colPname;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> coltotal;

    @FXML
    private Label lblNetTotal;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtMaterialId;

    @FXML
    private TextField txtMterialQty;

    @FXML
    private TableView<CartTm> tblcart;

    @FXML
    private TextField txtOUnitPRrce;
    @FXML
    private TextField txtOclientId;

    @FXML
    private TextField txtCnumber;

    @FXML
    private TextField txtOdate;

    @FXML
    private TextField txtOid;

    @FXML
    private TextField txtOproductName;

    @FXML
    private TextField txtOproductid;

    @FXML
    private TextField txtQty;
    private ObservableList<CartTm> cartList = FXCollections.observableArrayList();
    AddOrderBO addOrderBO  = (AddOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.AOD);
    private double netTotal = 0;
    private  String mQty  = null;
    private  String oId  = null;
    public void initialize() {

        setCellValueFactory();
        loadNextOrderId();
        getProductIds();
        getMaterialNames();
        setDate();
    }

    private void loadNextOrderId() {
    }

    private void getProductIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> pIdList = addOrderBO.getPIds();
            for (String id : pIdList) {
                obList.add(id);
            }

            cmbProductId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getMaterialNames() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> mIdList = addOrderBO.getMNames();
            for (String names : mIdList) {
                obList.add(names);
            }

            cmbMaterialName.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtOdate.setText(String.valueOf(now));
    }

    private void setCellValueFactory() {
        colOid.setCellValueFactory(new PropertyValueFactory<>("oId"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("cId"));
        colOContactNumber.setCellValueFactory(new PropertyValueFactory<>("cNumber"));
        colPid.setCellValueFactory(new PropertyValueFactory<>("pId"));
        colPname.setCellValueFactory(new PropertyValueFactory<>("pName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colMaterialQty.setCellValueFactory(new PropertyValueFactory<>("materialQtyTotal"));
    }

    public void txtKeyRelease (KeyEvent keyEvent){

    }

    public void txtClientContactNumberOnAction (ActionEvent event){
        String cNumber = txtCnumber.getText();
        try {
          Client client  = addOrderBO.searchByCNumber(cNumber);
            if (client != null) {
                txtOclientId.setText(client.getId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddProductOnAction (ActionEvent event){
        String oId = txtOid.getText();
        String cNumber = txtCnumber.getText();
        String cId = txtOclientId.getText();
        String pId = cmbProductId.getValue();
        String pName = txtOproductName.getText();
        double unitPrice = Double.parseDouble(txtOUnitPRrce.getText());
        int qty = Integer.parseInt((txtQty.getText()));
        String date = txtOdate.getText();
        String materialName = cmbMaterialName.getValue();
        String materialId = txtMaterialId.getText();
        int materialQty = Integer.parseInt(txtMterialQty.getText());
        double total = qty * unitPrice;
        String matirialQtyTotal = materialQty * qty +"m";
        mQty= matirialQtyTotal;

        for (int i = 0; i < tblcart.getItems().size(); i++) {
            if (pId.equals(colPid.getCellData(i))) {
                qty += cartList.get(i).getQty();
                total = unitPrice * qty;

                cartList.get(i).setQty(qty);
                cartList.get(i).setTotal(total);

                tblcart.refresh();
                calculateNetTotal();
                return;
            }
        }
        CartTm cartTm = new CartTm( oId, cId, cNumber , pId , pName , unitPrice ,qty ,date ,total, matirialQtyTotal);

        cartList.add(cartTm);

        tblcart.setItems(cartList);
        calculateNetTotal();
    }

    private void calculateNetTotal() {
        netTotal = 0;
        for (int i = 0; i < tblcart.getItems().size(); i++) {
            netTotal += (double) coltotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    public void btnAddOrderOnAction (ActionEvent event){
        oId = txtOid.getText();
        String name = txtOproductName.getText();
        String date = txtOdate.getText();
        String cid = txtOclientId.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String desc = cmbMaterialName.getValue();
        String mId = txtMaterialId.getText();
        int materialQty = Integer.parseInt(txtMterialQty.getText());
        String mName = cmbMaterialName.getValue();

        boolean b = false;

        b = saveOrder(oId,name,date,qty,cid, mId, mName,
            tblcart.getItems().stream().map(tm -> new OrderDetailDTO(oId,tm.getCId(), tm.getCNumber(),
                    tm.getPId(), tm.getPName(), tm.getUnitPrice(),tm.getQty(),
                    tm.getDate(),tm.getMaterialQtyTotal(),tm.getTotal())).collect(Collectors.toList()));

            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"order Placed!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION,"order not placed!").show();
            }
    }

    private boolean saveOrder(String oId, String name, String date, int qty, String cid, String mId, String mName, List<OrderDetailDTO> collect) {
        OrderDTO orderDTO = new OrderDTO(oId,name,date,qty,cid,collect);
        MaterialDetailDTO materialDetailDTO = new MaterialDetailDTO(oId,mName,mId,qty);
        return addOrderBO.saveOrder(orderDTO,materialDetailDTO);
    }


    public void btnClearOnAction (ActionEvent event){
    clear();
    }

    public void cmbProductIdOnAction (ActionEvent event){
        String id = cmbProductId.getValue();
        try {
            ProductDTO productDTO = addOrderBO.getProductDetails(id);
            txtOproductName.setText(productDTO.getDescription());
            txtOUnitPRrce.setText(String.valueOf(productDTO.getCostPerOne()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnGenarateQuotationOnAction (ActionEvent event){
    }

    public void cmbMaterialNameOnAction (ActionEvent event){
        String name = cmbMaterialName.getValue();
        MaterialDTO materialDTO = addOrderBO.getMaterialDeatils(name);
        if (materialDTO != null) {
            txtMaterialId.setText(materialDTO.getId());
      }

    }

    public void btnSendMailOnAction (ActionEvent event){
    }
    public void clear(){
        txtOid.clear();
        txtCnumber.clear();
        txtOclientId.clear();
        txtOproductName.clear();
        txtOUnitPRrce.clear();
        txtQty.clear();
        txtOdate.clear();
        txtMaterialId.clear();
        txtMterialQty.clear();

    }

}