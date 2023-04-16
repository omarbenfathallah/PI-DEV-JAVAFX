/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.AchatDAO;
import edu.esprit.dao.classes.OffreDAO;
import edu.esprit.entities.Achat;
import edu.esprit.entities.Offre;
import edu.esprit.entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class OffrefrontomarController implements Initializable {

    int id_offre;

    @FXML
    private ImageView fImg;
    @FXML
    private Label fnom;
    @FXML
    private Label fpnt;
    @FXML
    private Button Ach;

    AchatDAO aa = new AchatDAO();
    OffreDAO oo = new OffreDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      oo.setItems(FXCollections.observableArrayList(oo.getAll()));
    }

    private void liredescription(ActionEvent event) {
        Offre e = new Offre();

        System.out.println(e.getNom());
    }

    public void setEvenment(Offre oo) {
        fnom.setText(oo.getNom());
        fpnt.setText(Integer.toString(oo.getPoints()));

        String path = oo.getImage();
        File file = new File(path);
        Image img = new Image(file.toURI().toString());
        fImg.setImage(img);
        
        
    }

    public void setIdoffre(int id_offre) {
        this.id_offre = id_offre;
    }

    @FXML
    private void AcheterOffre(ActionEvent event) {

    }
}
