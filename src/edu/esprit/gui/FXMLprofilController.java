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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class FXMLprofilController implements Initializable {

    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname, txt_adresse;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txt_tel;

    User user = new User();

    UserService udao = new UserService();
    @FXML
    private ImageView userImg;
    @FXML
    private Label txtUsername;
    @FXML
    private Button logout;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDis();

        user = udao.DisplayById(Session.current_user.getId());
        
        txtFirstname.setText(user.getFirstname());
        txtLastname.setText(user.getLastname());
        txtEmail.setText(user.getEmail());
        txt_adresse.setText(user.getAdresse());
        txt_tel.setText(user.getNumero());
      


    }

    @FXML
    private void save(ActionEvent event) {
        if ("Modifier".equals(btnSave.getText())) {
            btnSave.setText("Save");
            txtFirstname.setDisable(false);
            txtLastname.setDisable(false);
            txtEmail.setDisable(false);
            txt_tel.setDisable(false);
            txt_adresse.setDisable(false);
        } else {
            String newEmail = txtEmail.getText().trim();
            if (!newEmail.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                txtEmail.setStyle("-fx-border-color: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            } else if (!txt_tel.getText().matches("[0-9]+")) {
                txt_tel.setStyle("-fx-border-color: red; -fx-text-box-border: red ; -fx-focus-color: red ; -fx-text-fill: red; -fx-font-size: 16px;");
            } else {
                String oldEmail = user.getEmail();
                if (!newEmail.equals(oldEmail)) {
                    if (udao.getEmail(newEmail) != null) {
                        Image img = new Image(getClass().getResourceAsStream("../images/close.png"));
                        Notifications notification = pushNotify("Email already exists", "Cancel");
                        notification.graphic(new ImageView(img));
                        notification.show();
                        txtEmail.setText(oldEmail);
                        return;
                    }
                }
                User u = new User();
                u.setFirstname(txtFirstname.getText());
                u.setLastname(txtLastname.getText());
                u.setEmail(newEmail);
                u.setNumero(txt_tel.getText());
                u.setAdresse(txt_adresse.getText());
                udao.modifier(u, Session.current_user.getId());
                Image img = new Image(getClass().getResourceAsStream("../images/ok.png"));
                Notifications notification = pushNotify("data updated", "");
                notification.graphic(new ImageView(img));
                notification.show();
                setDis();

            }

        }
    }

    public Notifications pushNotify(String title, String text) {
        Notifications notification = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(7))
                .position(Pos.TOP_RIGHT)
                .onAction((ActionEvent e) -> {
                    System.out.println("clicked on notification");
                });
        return notification;
    }

    public void setDis() {
        btnSave.setText("Modifier");
        txtFirstname.setDisable(true);
        txtLastname.setDisable(true);
        txtEmail.setDisable(true);
        txt_tel.setDisable(true);
        txt_adresse.setDisable(true);
    }

    @FXML
    private void logout(ActionEvent event) {
        Session.current_user = null;
        try {
            Parent page = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void gohome(MouseEvent event) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("FXMLuserlist.fxml"));
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
