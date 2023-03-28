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
import edu.esprit.entities.Categories;
import java.sql.Date;
import javafx.collections.ObservableList;
import edu.esprit.dao.classes.CategorieDAO;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AfficheAllAchatController implements Initializable {

    @FXML
    private TableColumn<?, ?> idachat;
    @FXML
    private TableColumn<?, ?> iduser;
    @FXML
    private TableColumn<?, ?> idoffre;
    @FXML
    private TableColumn<Categories, Date> date;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnR;
     ObservableList<Categories> obList;
  CategorieDAO  cat ;
     Categories c = new Categories();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
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
    
}
