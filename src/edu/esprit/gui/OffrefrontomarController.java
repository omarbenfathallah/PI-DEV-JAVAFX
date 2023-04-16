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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Base64;
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

    public void setEvenment(Offre oo) throws FileNotFoundException, IOException {
        fnom.setText(oo.getNom());
        fpnt.setText(Integer.toString(oo.getPoints()));

        String path = oo.getImage();
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
         
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

        fImg.setImage(image);
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
        // int id_us = id;
        int id_us = 22;
        Date date_achat = new Date();
        User user = new User(22);
        Offre offre = new Offre(id_off);
        Achat achat = new Achat(user, offre, date_achat);
        aa.insertAchat(achat);
    }
}
