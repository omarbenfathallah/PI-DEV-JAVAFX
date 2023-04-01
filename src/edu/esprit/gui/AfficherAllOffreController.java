/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.CategorieDAO;
import edu.esprit.dao.classes.OffreDAO;
import edu.esprit.entities.Categories;
import edu.esprit.entities.Offre;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AfficherAllOffreController implements Initializable {

    static String nom, description, image;
    static int idOf, pnt, idCat;

    ObservableList<Offre> obList;
    OffreDAO of = new OffreDAO();
    Offre o = new Offre();

    @FXML
    private TableColumn<Offre, Integer> idO;
    @FXML
    private TableColumn<Offre, String> nomO;
    @FXML
    private TableColumn<Offre, String> descO;
    @FXML
    private TableColumn<Offre, String> imgO;
    @FXML
    private TableColumn<Offre, Integer> pntO;
    @FXML
    private TableColumn<Offre, Integer> idcat;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnR;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnM;
    @FXML
    private TableView<Offre> afficherOffre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idO.setCellValueFactory(new PropertyValueFactory<>("id_offre"));
        nomO.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descO.setCellValueFactory(new PropertyValueFactory<>("description"));
        imgO.setCellValueFactory(new PropertyValueFactory<>("image"));
        pntO.setCellValueFactory(new PropertyValueFactory<>("points"));
        idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        List<Offre> list2 = of.DisplayAllOffres();
        afficherOffre.getItems().addAll(list2);
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

        TableView<Offre> tableView = this.afficherOffre;
        OffreDAO of = new OffreDAO();
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            Offre o = tableView.getSelectionModel().getSelectedItem();
            of.deleteOffre(o);
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

    @FXML
    private void ModifierOffre(ActionEvent event) {
        TableView<Offre> table = afficherOffre;
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une Categorie à modifier !!");
            alert.showAndWait();
        }

        Offre O = table.getSelectionModel().getSelectedItem();

        idOf = O.getId_offre();
        nom = O.getNom();
        description = O.getDescription();
        pnt = O.getPoints();
        image = O.getImage();
        idCat = O.getId_cat().getId_categorie();

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
