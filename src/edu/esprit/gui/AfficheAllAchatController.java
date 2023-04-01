/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.AchatDAO;
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


import javafx.collections.ObservableList;

import edu.esprit.entities.Achat;
import java.util.Date;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AfficheAllAchatController implements Initializable {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idachat.setCellValueFactory(new PropertyValueFactory<>("id_achat"));

        iduser.setCellValueFactory(new PropertyValueFactory<>("id_us"));

        idoffre.setCellValueFactory(new PropertyValueFactory<>("id_off"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_achat"));

        List<Achat> list2 = aa.DisplayAllAchat();
        affichageAchat.getItems().addAll(list2);

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
