/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static javax.swing.text.StyleConstants.Background;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class HomeController implements Initializable {

    @FXML
    private VBox centerVBox;
    @FXML
    private Button btnCnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image backgroundImage = new Image(getClass().getResourceAsStream("/GUI/home.jpg"));
        BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
        centerVBox.setBackground(new Background(background));

    }

    @FXML
    private void Connecter(ActionEvent event) {
          try {
            Parent page1 = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);
    }
}
}