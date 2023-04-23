/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.itextpdf.text.pdf.qrcode.BitMatrix;
import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import edu.esprit.dao.classes.AchatDAO;
import edu.esprit.dao.classes.OffreDAO;
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
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class OffrefrontomarController implements Initializable {

    int id_offre;
    int id;

    @FXML
    private GridPane gridoffre;

    @FXML
    private AnchorPane pane;

    Offre o = new Offre();

    @FXML
    private ImageView fImg;
    @FXML
    private Label fnom;
    @FXML
    private Label fpnt;

    AchatDAO aa = new AchatDAO();
    @FXML
    private Button btnD;
    @FXML
    private Button btnQr;

// Declare a variable to store the selected offer
    private Offre selectedOffre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridoffre = new GridPane();
//        this.gridoffre=gridoffre;

        // TODO
    }

    private void liredescription(ActionEvent event) {
        Offre e = new Offre();

        System.out.println(e.getNom());
    }

    Offre getEvenment() {
        return o;
    }

    void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public void setEvenment(Offre offre) throws FileNotFoundException, IOException {
        this.o = offre;

        fnom.setText(offre.getNom());
        fpnt.setText(Integer.toString(offre.getPoints()));

        String path = offre.getImage();
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        //

        fImg.setImage(image);

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
//        this.offre = offre;
//        titre.setText(offre.getTitre());
//        description.setText(offre.getDescription());
//        image.setImage(new Image(offre.getImage()));
//        date_debut.setText(offre.getDate_debut().toString());
//        date_fin.setText(offre.getDate_fin().toString());
//
//        // Add event handler for Details button
//        detailsButton.setOnAction(event -> {
//            DeatailsOffre(offre);
//        });
    }

    public void setIdoffre(int id_offre) {
        this.id_offre = id_offre;
    }

    public int getIdoOffre() {
        return id_offre;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void DetailsOffres(ActionEvent event) {

//        int id_off = id_offre; // get the ID of the current offer
//        int id_us = id ; // get the ID of the current user
//        Date date_achat = new Date(); // get the current date
//         aa.insertAchat(new Achat(id_us, id_off, date_achat));
        //int id_off = id_offre;
        // int id_us = id;
        int id_us = 22;
        Date date_achat = new Date();
        User user = new User(22);
        Offre offre = new Offre(getIdoOffre());
        Achat achat = new Achat(user, offre, date_achat);
        aa.insertAchat(achat);
    }

    @FXML
    private void DetailsOffre(ActionEvent event) {

    }

//    @FXML
//    private void handleDetailsButtonAction(ActionEvent event) throws IOException {
//
////        ObservableList<Offre> offres;
//        OffreDAO offreDao = new OffreDAO();
////        offres = offreDao.OffresQR();
//
////        Offre offre = new Offre(getIdoOffre());
////          Offre offres = new Offre(getIdoOffre());
//        List<Offre> offres = offreDao.OffresQR();
//        for (Offre offre : offres){
//        String filePath = "C:/Users/BAZINFO/Documents/NetBeansProjects/Utri/qrcode_" + offre.getId_offre() + ".png";
//        try {
//            QrcodeController.generateQrCode(offre, filePath);
//        } catch (Exception e) {
//            // Handle any errors
//            e.printStackTrace();
//        }
//        
//
//        // Create a new window to display the QR code
//        Stage qrCodeStage = new Stage();
//        qrCodeStage.setTitle("Offer Details");
//        qrCodeStage.initModality(Modality.APPLICATION_MODAL);
//        qrCodeStage.setResizable(false);
//
//        // Load the FXML for the QR code window
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("qrcode.fxml"));
//        Parent root = loader.load();
//        QrcodeController qrCodeController = loader.getController();
//        qrCodeController.setQrCodeImage(filePath);
//        
//        // Set the scene and show the window
//        Scene scene = new Scene(root);
//        qrCodeStage.setScene(scene);
//        qrCodeStage.showAndWait();
//        }
//    }
}
