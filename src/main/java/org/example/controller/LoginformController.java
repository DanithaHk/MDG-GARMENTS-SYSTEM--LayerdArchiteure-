package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.db.DbConnection;
//import lk.ijse.Util.ValidateUtil;
//import lk.ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginformController {
    public TextField txtUserId;
    public TextField txtPassword;
    @FXML
    private AnchorPane rootNode;
    static String user = null;



    public void btnLoginOnAction(ActionEvent event) throws IOException {
        boolean isValidate = validateLogin();
        if(isValidate) {
            String userId = txtUserId.getText();
            String pw = txtPassword.getText();

            try {
                checkCredential(userId, pw);

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "OOPS! something went wrong").show();
            }
        }
    }

    private boolean validateLogin() {
        int num = 0;
        String userId = txtUserId.getText();
        boolean isUserIdValidate = Pattern.matches("[A-z]{3,}", userId);
        if (!isUserIdValidate) {
            // new Alert(Alert.AlertType.ERROR, "INVALID Name").show();
            num = 1;
           // ValidateUtil.vibrateTextField(txtUserId);
        }
        String pw = txtPassword.getText();
        boolean isPwValidate = Pattern.matches("[A-z0-9]{3,}", pw);
        if (!isPwValidate){
            num =1;
           // ValidateUtil.vibrateTextField(txtPassword);
        }
        if (num == 1) {
            num = 0;
            return false;
        } else {
            num = 0;
            return true;
        }
    }

    private void checkCredential(String userId, String pw) throws SQLException, IOException {
        String sql = "SELECT username, password FROM user WHERE username = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String dbPw = resultSet.getString(2);
            user = userId;
            if(dbPw.equals(pw)) {
                navigateToTheDashboard();

            } else {
                new Alert(Alert.AlertType.ERROR, "Password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "user id not found!").show();
        }
    }


    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashbord.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("dashbord Form");
    }
    public void BnSignUp(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/signupPage.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("MDG GARMENTS");
    }

}





