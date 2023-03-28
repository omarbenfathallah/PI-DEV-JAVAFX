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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class UpdateOffreController implements Initializable {

    @FXML
    private TextField Mnom;
    @FXML
    private TextField Mdesc;
    @FXML
    private TextField Mpnt;
    @FXML
    private TextField Mcat;
    @FXML
    private TextField Mimg;
    @FXML
    private Button MbtnUpload;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void UploadImage(ActionEvent event) {
    }

    @FXML
    private void AjoutOffre(ActionEvent event) {
    }

    @FXML
    private void RetourAccueil(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("AfficherAllOffre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
             System.out.println(ex);
        }
    }
    
}
