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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    @FXML
    private TextField Acat;
    @FXML
    private TextField Aimg;
    @FXML
    private Button btnUpload;
    @FXML
    private Button btnAjout;
     Offre e = new Offre();
   OffreDAO se = new OffreDAO();
    @FXML
    private Button btnR;
    @FXML
    private ImageView Aimgg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        String DBPath = "C:\\\\xampp\\\\htdocs\\\\Captures\\\\"  + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            Aimgg.setImage(img);    
            Aimg.setText(DBPath);
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
    private void AjoutOffre(ActionEvent event) {
        
        String nom = Anom.getText();
        String desc = Adesc.getText();
        int pnt = Integer.parseInt(Apnt.getText());
        int Cat = Integer.parseInt(Acat.getText());       
        String timg = Aimg.getText();
        
         if(nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez entrer nom d'Offre!");
            alert.show();
        } else if(nom.length() < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Le nom de l'offre doit comporter au moins 3 caractères!");
            alert.show();
        } else if(desc.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez entrer une description d'offre!");
            alert.show();
        } else if(pnt <0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez entrer le nombre de points pour l'offre!");
            alert.show();
        } else if(Cat <0 ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez sélectionner une catégorie pour l'offre!");
            alert.show();
        } else if(timg == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Erreur! Veuillez sélectionner une image pour l'offre!");
            alert.show();
        }
         else {
            se.insertOffre(new Offre(nom, desc, timg, pnt, Cat));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Categorie ajouté avec succés !");
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
