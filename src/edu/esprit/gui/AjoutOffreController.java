/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.OffreDAO;
import edu.esprit.dao.classes.CategorieDAO;

import edu.esprit.entities.Offre;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AjoutOffreController implements Initializable {

    @FXML
    private TextField Anom;
    @FXML
    private TextField Adesc;
    @FXML
    private TextField Apnt;
    private TextField Acat;
    @FXML
    private TextField Aimg;
    @FXML
    private Button btnUpload;
    @FXML
    private Button btnAjout;

    Offre e = new Offre();
    OffreDAO of = new OffreDAO();
    @FXML
    private Button btnR;
    @FXML
    private ImageView Aimgg;
    @FXML
    private ComboBox<String> Allcat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Allcat.setItems(FXCollections.observableArrayList(of.getAll()));
    }

    @FXML
    private void UploadImage(ActionEvent event) {
//        FileChooser fc = new FileChooser();
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.bmp");
//        fc.getExtensionFilters().add(extFilter);
//        File file = fc.showOpenDialog(null);
//
//        if (file != null) {
//            String path = file.getPath();
//
//            try {
//                FileInputStream stream = new FileInputStream(path);
//                int bufLength = 2048;
//                byte[] buffer = new byte[2048];
//                byte[] data;
//
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                int readLength;
//                while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
//                    out.write(buffer, 0, readLength);
//                }
//
//                data = out.toByteArray();
//                String imageString = Base64.getEncoder().withoutPadding().encodeToString(data);
//
//                out.close();
//                stream.close();
//
//                byte[] imageData = Base64.getDecoder().decode(imageString);
//                Image image = new Image(new ByteArrayInputStream(imageData));
//
//               Aimgg.setImage(image);
//               Aimg.setText(imageString);;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
//               Aimg.setText(selectedFile.getName());
//               Aimgg.setImage(new Image(selectedFile + "file:"));
            try {
                String imagePath = selectedFile.getAbsolutePath();
                String encodedImage = imageEncoderDecoder(imagePath);
                Aimg.setText(imagePath);
                Image image = new Image("data:image/png;base64," + encodedImage);
                Aimgg.setImage(image);
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error uploading image!");
                alert.showAndWait();
            }
        }
    }

    public String imageEncoderDecoder(String path) throws IOException {
        FileInputStream stream = new FileInputStream(path);
        int bufLength = 2048;
        byte[] buffer = new byte[2048];
        byte[] data;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int readLength;
        while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
            out.write(buffer, 0, readLength);
        }

        data = out.toByteArray();
        String imageString = Base64.getEncoder().withoutPadding().encodeToString(data);

        out.close();
        stream.close();
        return imageString;
    }

    @FXML
    private void AjoutOffre(ActionEvent event) throws SQLException {

        String id_cat = Allcat.getValue();
        OffreDAO of = new OffreDAO();
        int idC = of.chercherIdCat(id_cat);

        String nom = Anom.getText();
        String desc = Adesc.getText();
        String pntText = Apnt.getText();
        String timg = Aimg.getText();

        if (nom.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez entrer nom d'Offre!");
            alert.show();
        } else if (nom.length() < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Le nom de l'offre doit comporter au moins 3 caractères!");
            alert.show();
        } else if (desc.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez entrer une description d'offre!");
            alert.show();
        } else if (pntText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez entrer le nombre de points pour l'offre!");
            alert.show();
        } else if (!pntText.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Le nombpre de points doit etre un entier ");
            alert.show();
        } else if (id_cat == null || id_cat.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez sélectionner une catégorie!");
            alert.show();
        } else if (timg == null || timg.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez sélectionner une image pour l'offre!");
            alert.show();
        } else {
            int pnt = Integer.parseInt(pntText);
            //  int cat = Integer.parseInt(catText);
            of.insertOffre(new Offre(nom, desc, timg, pnt, idC));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Offre ajouté avec succès !");
            alert.showAndWait();
        }
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

//        CategorieDAO categorie = Acat.getSelectionModel().getSelectedItem();
//        if (categorie == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Erreur! Veuillez sélectionner une catégorie pour l'offre!");
//            alert.show();
//            return; // exit the event handler
//        } else {
//            se.insertOffre(new Offre(nom, desc, timg, pnt, categorie.getId()));
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Success Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Offre ajoutée avec succès !");
//            alert.showAndWait();
//        }
