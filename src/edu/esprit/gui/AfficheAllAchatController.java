/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

<<<<<<< HEAD
import edu.esprit.dao.classes.AchatDAO;
=======
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
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
<<<<<<< HEAD


import javafx.collections.ObservableList;

import edu.esprit.entities.Achat;
import java.util.Date;
import java.util.List;
import javafx.scene.control.TableView;
=======
import edu.esprit.entities.Categories;
import java.sql.Date;
import javafx.collections.ObservableList;
import edu.esprit.dao.classes.CategorieDAO;
import java.util.List;
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AfficheAllAchatController implements Initializable {
<<<<<<< HEAD
//    static int IdAchat;
//    static int IdUs;
//    static int IdOf;
//    static Date dt ;

    @FXML
    private TableColumn<Achat, Integer> idachat;
    @FXML
    private TableColumn<Achat, Integer> iduser;
    @FXML
    private TableColumn<Achat, Integer> idoffre;
    @FXML
    private TableColumn<Achat,Date> date;
    @FXML
    private Button btnR;
    ObservableList<Achat> obList;
    AchatDAO aa = new AchatDAO(); 
    Achat a = new Achat();
    @FXML
    private TableView<Achat> affichageAchat;
=======

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
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
<<<<<<< HEAD
        idachat.setCellValueFactory(new PropertyValueFactory<>("id_achat"));

        iduser.setCellValueFactory(new PropertyValueFactory<>("id_us"));

        idoffre.setCellValueFactory(new PropertyValueFactory<>("id_off"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_achat"));

        List<Achat> list2 = aa.DisplayAllAchat();
        affichageAchat.getItems().addAll(list2);

=======
      
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
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
