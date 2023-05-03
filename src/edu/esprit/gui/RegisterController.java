/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.UserService;
import edu.esprit.entities.User;
import edu.esprit.util.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import edu.esprit.util.MyConnection;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtPassword2;

    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnSignin;
    @FXML
    Label lblStatus;

    PreparedStatement preparedStatement;
    Connection con;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public RegisterController() {
        con = (Connection) MyConnection.getInstance().getConnection();
    }

    UserService udao = new UserService();
    User u = new User();

    @FXML
    private void HandleEvents(MouseEvent event) {

        if (txtEmail.getText().isEmpty() || txtFirstname.getText().isEmpty() || txtLastname.getText().isEmpty() || txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter all details");
        } else if (udao.getEmail(txtEmail.getText()) != null) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Email already exists");
            txtEmail.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtPhone.setStyle("");
            txtPassword.setStyle("");
            txtPassword2.setStyle("");
        } else if (!txtEmail.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter valid Email");
            txtEmail.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtPhone.setStyle("");
            txtPassword.setStyle("");
            txtPassword2.setStyle("");
        } else if (!txtPhone.getText().matches("[0-9]+")) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter valid phone number");
            txtPhone.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtEmail.setStyle("");
            txtPassword.setStyle("");
            txtPassword2.setStyle("");
        } else if (txtPassword.getText() == null ? txtPassword2.getText() != null : !txtPassword.getText().equals(txtPassword2.getText())) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("passwords don't match");
            txtPassword.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtPassword2.setStyle("-fx-border-clor: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            txtPhone.setStyle("");
            txtEmail.setStyle("");
        } else {
            u.setFirstname(txtFirstname.getText());
            u.setLastname(txtLastname.getText());
            u.setEmail(txtEmail.getText());
            u.setNumero(txtPhone.getText());
            u.setPassword(txtPassword.getText());
            u.setAdresse(txtAddress.getText());

            if (udao.ajouter(u)) {
                lblStatus.setTextFill(Color.GREEN);
                lblStatus.setText("registration complete");
                if (logIn() == true) {
                    try {
                        if (udao.isAdmin(Session.current_user.getId()) == true) {
                            Parent page = FXMLLoader.load(getClass().getResource("FXMLuserlist.fxml"));
                            Scene scene = new Scene(page);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        } else {
                            Parent page = FXMLLoader.load(getClass().getResource("FXMLuserlist.fxml"));
                            Scene scene = new Scene(page);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                        }

                    } catch (IOException ex) {

                        Logger.getLogger(FXMLprofilController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        }
        if (event.getSource() == btnSignin) {
            clearFields();
            try {
                Parent page = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public boolean logIn() {

        String username = txtEmail.getText();
        String password = txtPassword.getText();
        boolean bool = false;

        if (u.signup(username, password)) {

            System.out.println("It matches");

            int id = udao.getId(username);
            Session.current_user = new User();
            Session.current_user.setId(id);
            System.out.println("yoooo");
            bool = true;

        }

        return bool;

    }

    private void clearFields() {
        txtFirstname.clear();
        txtLastname.clear();
        txtEmail.clear();
        txtAddress.clear();
        txtPhone.clear();
        txtPassword.clear();
        txtPassword2.clear();

    }

}
