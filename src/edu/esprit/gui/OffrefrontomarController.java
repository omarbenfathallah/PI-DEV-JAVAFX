/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.AchatDAO;
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
import java.util.Date;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class OffrefrontomarController implements Initializable {

    int id_offre;
      int id;

    @FXML
    private ImageView fImg;
    @FXML
    private Label fnom;
    @FXML
    private Label fpnt;
    @FXML
    private Button Ach;

    AchatDAO aa = new AchatDAO();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
     public void setId(int id) {
        this.id = id;
    }
    
    @FXML
    private void AcheterOffre(ActionEvent event) {

//        int id_off = id_offre; // get the ID of the current offer
//        int id_us = id ; // get the ID of the current user
//        Date date_achat = new Date(); // get the current date
//         aa.insertAchat(new Achat(id_us, id_off, date_achat));

         
            int id_off = id_offre;
     //   int id_us = id;
         int id_us = 22 ;
        Date date_achat = new Date();
        User user = new User(22);
        Offre offre = new Offre(id_off);
        Achat achat = new Achat(user ,offre, date_achat);
        aa.insertAchat(achat);
    }
}
