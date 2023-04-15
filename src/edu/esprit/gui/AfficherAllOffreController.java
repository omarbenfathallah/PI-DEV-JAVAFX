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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    OffreDAO of;
    Offre o = new Offre();
    ObservableList<Offre> obList;

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
    @FXML
    private TextField search;
    @FXML
    private Button Recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        of = new OffreDAO();
        obList = of.DisplayAllOffres();

        // idO.setCellValueFactory(new PropertyValueFactory<>("id_offre"));
        nomO.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descO.setCellValueFactory(new PropertyValueFactory<>("description"));
        imgO.setCellValueFactory(new PropertyValueFactory<>("image"));
        pntO.setCellValueFactory(new PropertyValueFactory<>("points"));
        idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        
        OffreDAO od = new  OffreDAO();
        List<Offre> list2 = od.DisplayAllOffres();
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

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette offre ?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                of.deleteOffre(o);
                tableView.getItems().remove(selectedIndex);
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Offre supprimée avec succès !");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fail Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une offre à supprimer !");
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
            alert.setContentText("Veuillez sélectionner un Offre à modifier !!");
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

    @FXML
    private void rechercheOffre(ActionEvent event) {

        choose();

    }

    public void choose() {

//         idOf.setCellValueFactory(new PropertyValueFactory<>("id_offre"));
        nomO.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descO.setCellValueFactory(new PropertyValueFactory<>("description"));
        imgO.setCellValueFactory(new PropertyValueFactory<>("image"));
        pntO.setCellValueFactory(new PropertyValueFactory<>("points"));
        idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
//  obList = of.DisplayAllOffres();
        FilteredList<Offre> filteredData = new FilteredList<>(obList, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(off -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (off.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Offre> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(afficherOffre.comparatorProperty());
        afficherOffre.setItems(sortedData);
    }
//    public void choose() {
//    idO.setCellValueFactory(new PropertyValueFactory<>("id_offre"));
//    nomO.setCellValueFactory(new PropertyValueFactory<>("nom"));
//    descO.setCellValueFactory(new PropertyValueFactory<>("description"));
//    imgO.setCellValueFactory(new PropertyValueFactory<>("image"));
//    pntO.setCellValueFactory(new PropertyValueFactory<>("points"));
//    idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
//
//    FilteredList<Offre> filteredData = new FilteredList<>(obList, b -> true);
//    search.textProperty().addListener((observable, oldValue, newValue) -> {
//        filteredData.setPredicate(off -> {
//            if (newValue == null || newValue.isEmpty()) {
//                return true;
//            }
//            String[] keywords = newValue.toLowerCase().split("\\s+");
//            for (String keyword : keywords) {
//                boolean match = off.getNom().toLowerCase().contains(keyword)
//                        || off.getDescription().toLowerCase().contains(keyword)
//                || off.getId_cat() != null && off.getId_cat().getNomC().toLowerCase().contains(keyword);
//
//
//                if (!match) {
//                    return false;
//                }
//            }
//            return true;
//        });
//    });
//    SortedList<Offre> sortedData = new SortedList<>(filteredData);
//    sortedData.comparatorProperty().bind(afficherOffre.comparatorProperty());
//    afficherOffre.setItems(sortedData);  }

}
