/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import edu.esprit.dao.classes.CategorieDAO;
import edu.esprit.dao.classes.OffreDAO;
import edu.esprit.entities.Categories;
import edu.esprit.entities.Offre;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AfficherAllOffreController implements Initializable {

    static String nom, description, image;
    static int idOf, pnt, idCat;
    ImageView ph ;
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
    @FXML
    private Button btnQr;
//    private ImageView imgOff;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        of = new OffreDAO();
        obList = of.DisplayAllOffres();

//          String path = offre.getImage();
//        File file = new File(path);
//        Image image = new Image(file.toURI().toString());

//        String path = o.getImage();
//        File file = new File(path);
//        Image image = new Image(file.toURI().toString());
//        //
//
//
//         imgOff.setImage(image);
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
            Parent page1 = FXMLLoader.load(getClass().getResource("FXMLuserList.fxml"));
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

    @FXML
    private void QrCode(ActionEvent event) {
         Stage qrStage = new Stage();
        Offre p;
        
        p=afficherOffre.getSelectionModel().getSelectedItem();
        OffreDAO pd=new OffreDAO();
        pd.Qr(qrStage,p);
    }
    


}
