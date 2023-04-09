/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.OffreDAO;
import edu.esprit.entities.Offre;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private Button btnAjout;
    @FXML

    private Button btnR;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Mnom.setText(AfficherAllOffreController.nom);
        Mdesc.setText(AfficherAllOffreController.description);
        Mpnt.setText(String.valueOf(AfficherAllOffreController.pnt));
        Mcat.setText(String.valueOf(AfficherAllOffreController.idCat));
    }

    @FXML
    private void ModifierOffre(ActionEvent event) throws SQLException{

        String nom = Mnom.getText();
        String desc = Mdesc.getText();
        int pnt = Integer.parseInt(Mpnt.getText());
        int idc = Integer.parseInt(Mcat.getText());

        OffreDAO dao = new OffreDAO();
        Offre O = new Offre(AfficherAllOffreController.idOf, nom, desc, pnt,AfficherAllOffreController.image, idc);
        dao.updateOffre(O);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success Message");
        alert.setHeaderText(null);
        alert.setContentText("Offre modifié avec succés !");
        alert.showAndWait();
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
