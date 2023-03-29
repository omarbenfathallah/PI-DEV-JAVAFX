/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    package edu.esprit.gui ;

import javafx.scene.control.TableView ;
import edu.esprit.dao.classes.CategorieDAO;
import edu.esprit.entities.Categories;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AfficherAllCategorieController implements Initializable {

    static int IdCat;
    static String nom;
    static String desc;

    ObservableList<Categories> obList;
    CategorieDAO ca = new CategorieDAO();
    @FXML
    private Button btnM;

    @FXML
    private Button btnR;

    @FXML
    private Button btnSupp;

    @FXML
    private Button btnajout;

    @FXML
    private TableColumn<Categories, Integer> idCat;

    @FXML
    private TableColumn<Categories, String> nomCat;
    @FXML
    private TableColumn<Categories, String> descCat;

    @FXML
    private TableView<Categories> afficherCategoriee;


        
    public void initialize(URL url, ResourceBundle rb) {
        ca = new CategorieDAO();
    //    obList = ca.DisplayAllCategories();
        idCat.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        nomCat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descCat.setCellValueFactory(new PropertyValueFactory<>("description"));
        List<Categories> list2 = ca.DisplayAllCategories();
        afficherCategoriee.getItems().addAll(list2);
//        
    }    
        
        
        @FXML
        void AjoutCategorie(ActionEvent event) {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("AjoutCategorie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }

        @FXML
    void ModifierCategorie(ActionEvent event) {
        TableView<Categories> table = afficherCategoriee;
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une Categorie à modifier !!");
            alert.showAndWait();
        }

        Categories C = table.getSelectionModel().getSelectedItem();

        IdCat = C.getId_categorie();
        nom = C.getNom();
        desc = C.getDescription();

        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("UpdateCatgorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

        @FXML
        void RetourAccueil(ActionEvent event) {
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
    void SuppCategorie(ActionEvent event) {
        TableView<Categories> tableView = this.afficherCategoriee;
        CategorieDAO cc = new CategorieDAO();
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Categories c = tableView.getSelectionModel().getSelectedItem();
            cc.deleteCategories(c);
            tableView.getItems().remove(selectedIndex);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Categories supprimé avec succés !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un Categories à supprimer !!");
            alert.showAndWait();
        }

    }

}

  
