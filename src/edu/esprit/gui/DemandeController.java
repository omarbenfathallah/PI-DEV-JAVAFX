/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.DemandeP;
import edu.esprit.util.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



public class DemandeController implements Initializable {

    @FXML
    private Label TTded;


        @FXML
    private Button Add_Poubelle;

    @FXML
    private Button Close;


    @FXML
    private TextField Quantit_Pubelle;


      @FXML
    private TextField POUBELLE;

    @FXML
    private DatePicker date_dde;
@FXML
    private Label NumDde;
    @FXML
    private TextField etat_Dde;

    @FXML
    private TextField ref_dde;

     private Connection connect;
    private PreparedStatement prepare;
     private ResultSet result;
      private double x = 0;
    private double y = 0;
    @FXML
    private AnchorPane DEE_Form;
    @FXML
    private Label TTded1;


   
    @FXML
    public void AjouterDDEPoubelleADD() throws SQLException {
    String sql = "INSERT INTO demande_poubelle (ref, date_demande, etat_demande, poubel, quantite) VALUES (?,?,?,?,?)";
    Connection  connect = MyConnection.getInstance().getConnection();
    PreparedStatement prepare = null;
    Alert alert;

    try {
        String ref = ref_dde.getText();
        String dateDem = String.valueOf(date_dde.getValue());
        String etatDem = etat_Dde.getText();
        String poubel = POUBELLE.getText();
        String quantite = Quantit_Pubelle.getText();

        if (ref == null || dateDem == null || etatDem == null || poubel == null || quantite == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        } else {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, ref);
            prepare.setString(2, dateDem);
            prepare.setString(3, etatDem);
            prepare.setString(4, poubel);
            prepare.setString(5, quantite);

            int rowsInserted = prepare.executeUpdate();
            if (rowsInserted > 0) {
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully added!!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Code to execute when OK is pressed
                } else {
                    return;
                }
            }
        }
    } catch (SQLException e) {
        // Code to handle the exception
    } finally {
        if (prepare != null) {
            prepare.close();
        }
    }

}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
       public void close (){
        System.exit(0);
    }
    
}
