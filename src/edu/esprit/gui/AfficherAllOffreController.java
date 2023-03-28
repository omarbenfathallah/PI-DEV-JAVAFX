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
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AfficherAllOffreController implements Initializable {

    @FXML
    private TableColumn<?, ?> idO;
    @FXML
    private TableColumn<?, ?> nomO;
    @FXML
    private TableColumn<?, ?> descO;
    @FXML
    private TableColumn<?, ?> imgO;
    @FXML
    private TableColumn<?, ?> pntO;
    @FXML
    private TableColumn<?, ?> idcat;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnR;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterOffre(ActionEvent event) {
                  try {
            Parent page1 = FXMLLoader.load(getClass().getResource("AjoutOffre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
             System.out.println(ex);
        }
    }

    @FXML
    private void RetourAccueil(ActionEvent event) {
                  try {
            Parent page1 = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
             System.out.println(ex);
        }
    }

    @FXML
    private void SuppOffre(ActionEvent event) {
    }

    @FXML
    private void ModifierOffre(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("UpdateOffre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
             System.out.println(ex);
        }
    }
    
}
