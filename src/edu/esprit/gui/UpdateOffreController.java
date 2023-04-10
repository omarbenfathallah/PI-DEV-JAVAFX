/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.OffreDAO;
import edu.esprit.entities.Offre;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class UpdateOffreController implements Initializable {

    Offre e = new Offre();
    OffreDAO of = new OffreDAO();

    @FXML
    private TextField Mnom;
    @FXML
    private TextField Mdesc;
    @FXML
    private TextField Mpnt;
    @FXML
    private ComboBox<String> Mcat;
    @FXML
    private Button btnAjout;
    @FXML

    private Button btnR;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private TextField Mimg;
    @FXML
    private Button btnUpload;
    @FXML
    private ImageView Aimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Mnom.setText(AfficherAllOffreController.nom);
        Mdesc.setText(AfficherAllOffreController.description);
        Mpnt.setText(String.valueOf(AfficherAllOffreController.pnt));
        //  Mimg.setText(String.valueOf(AfficherAllOffreController.image));
        Mcat.setItems(FXCollections.observableArrayList(of.getAll()));
    }

    @FXML
    private void ModifierOffre(ActionEvent event) throws SQLException {

        String id_cat = Mcat.getValue();
        OffreDAO of = new OffreDAO();
        int idC = of.chercherIdCat(id_cat);

        String nom = Mnom.getText();
        String desc = Mdesc.getText();
        int pnt = Integer.parseInt(Mpnt.getText());

        OffreDAO dao = new OffreDAO();
        Offre O = new Offre(AfficherAllOffreController.idOf, nom, desc, pnt, idC);
        dao.updateOffre(O);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success Message");
        alert.setHeaderText(null);
        alert.setContentText("Offre modifiée avec succès!");
        alert.showAndWait();
    }

    @FXML
    private void UploadImage(ActionEvent event) throws FileNotFoundException, IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\xampp\\\\htdocs\\\\Captures\\\\" + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path = file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            Aimg.setImage(img);
            Mimg.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
        } else {
            System.out.println("error");
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
