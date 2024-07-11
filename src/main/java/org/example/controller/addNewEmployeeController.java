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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.BOFactory;
import org.example.bo.custom.EmployeeBO;
import org.example.bo.custom.impl.EmployeeBOImpl;
import org.example.dto.EmployeeDTO;
import org.example.entity.Employee;
import org.example.view.tdm.EmployeeTm;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class addNewEmployeeController {

    @FXML
    private TableView<EmployeeTm> addemployeetable;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colJobRole;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private AnchorPane rootaddNewEmployee;

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
    private TextField txtUsername;
    EmployeeBO employeeBO  = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    private List<EmployeeDTO> employeeList = new ArrayList<>();
    private LinkedHashMap<TextField,Pattern> map =new LinkedHashMap<>();
   // EmployeeBOImpl employeeBO =new EmployeeBOImpl() ;
    EmployeeBO employeeBO1  = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize() throws SQLException, ClassNotFoundException {
        Pattern patternId = Pattern.compile("^(E0)[0-9]{5}$");
        map.put(txtEid,patternId);

        setCellValueFactory();
        loadEmployeeTable();
        txtEid.setText(loadnextId());
    }

    private String loadnextId() {
        try {
            //Generate New ID
            return employeeBO.generateNewCustomerID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (addemployeetable.getItems().isEmpty()) {
            return "E00-001";
        } else {
            String id = getLastEmployeeId();
            int newCustomerId = Integer.parseInt(id.replace("E", "")) + 1;
            return String.format("E00", newCustomerId);
        }
    }

    private String getLastEmployeeId() {
        List<EmployeeTm> tempCustomersList = new ArrayList<>(addemployeetable.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getId();

    }

//    private List<Employee> getAllEmployees() {
//        List<Employee> employeeList = null;
//        try {
//            employeeList = AddEmployeeRepo.getAll();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return employeeList;
//    }

    private void setCellValueFactory() {
        addemployeetable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        addemployeetable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        addemployeetable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        addemployeetable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        addemployeetable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("JobRole"));
        addemployeetable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("username"));

    }

    private void loadEmployeeTable()  {
        addemployeetable.getItems().clear();
        /*Get all customers*/
        ArrayList<EmployeeDTO> allEmployee = null;
        try {
            allEmployee = employeeBO1.getAllEmployee();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(allEmployee);
        for (EmployeeDTO e : allEmployee) {
            addemployeetable.getItems().add(new EmployeeTm(e.getId(), e.getName(), e.getAddress(),e.getContactNumber(),e.getJobRole(),e.getUsername()));
        }


    }



    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {

        String id = txtEid.getText();
        String name = txtEname.getText();
        String address = txtEaddress.getText();
        String contactNumber = txtEcontactNumber.getText();
        String jobRole = txtEjobRole.getText();
        String userName = txtUsername.getText();


        boolean isSaved = false;
        try {
            isSaved = employeeBO1.addEmployee(new EmployeeDTO(id,name,address,contactNumber,jobRole,userName));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                clear();
                initialize();
            }
        } catch (SQLException | ClassNotFoundException e) {
             new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashbord.fxml"));


        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootaddNewEmployee.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("MDG GARMENT");
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();

    }
    public void clear() {
       txtEid.clear();
       txtEname.clear();
       txtEaddress.clear();
       txtEcontactNumber.clear();
       txtEjobRole.clear();
       txtUsername.clear();
    }
    @FXML
    void txtKeyRelese(KeyEvent event) {


    }

    @FXML
    void txtAddEmployeeIdOnAction(ActionEvent event) throws SQLException {
    }


    public void txtKeyRelese(javafx.scene.input.KeyEvent keyEvent) {
    }
}
