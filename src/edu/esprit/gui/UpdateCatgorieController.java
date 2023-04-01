/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.CategorieDAO;
import edu.esprit.entities.Categories;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class UpdateCatgorieController implements Initializable {

    @FXML
    private Button btnm;
    @FXML
    private TextField mnom;
    @FXML
    private TextField mdesc;
    @FXML
    private Button btnR;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mnom.setText(AfficherAllCategorieController.nom);
        mdesc.setText(AfficherAllCategorieController.desc);
    }    

    @FXML
    private void modifCategorie(ActionEvent event) {
        String nom = mnom.getText();
        String desc = mdesc.getText();

        CategorieDAO ca = new CategorieDAO();
        Categories c = new Categories(AfficherAllCategorieController.IdCat, nom, desc);
        ca.updateCategories(c);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success Message");
        alert.setHeaderText(null);
        alert.setContentText("Categorie modifié avec succés !");
        alert.showAndWait();
    }

    @FXML
    private void RetourCategorie(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("AfficherAllCategorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
             System.out.println(ex);
        }
    }
    
}
