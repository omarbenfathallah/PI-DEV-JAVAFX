/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.itextpdf.text.pdf.qrcode.WriterException;
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

    AchatDAO aa = new AchatDAO();
    @FXML
    private Button btnD;

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
    
    public int getIdoOffre() {
        return id_offre;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FXML
    private void DetailsOffre(ActionEvent event) {

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

//    @FXML
//    private void DetailsOffre(ActionEvent event)throws WriterException {// Get the selected Destination object from the ListView
// // Get the selected Destination object from the ListView
//    User selectedDest =list_afficher.getSelectionModel().getSelectedItem();
//    Offre selectedOf = 
//
//    if (selectedDest != null) {
//        // Get the name of the "nom", "prenom" and "role" from the selected Destination
//        String nom = selectedDest.getNom_user();
//        String prenom= selectedDest.getPrenom_user();
//        String role = selectedDest.getRole_user();
//
//        // Generate the QR code with the paysName, etoile and type as the content
//        String content = nom + "|" + prenom + "|" + role; // Use the pipe symbol as separator
//        int width = 300;
//        int height = 300;
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
//
//        // Convert the BitMatrix to a BufferedImage
//        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
//
//        // Display the image in a new window
//        Stage stage = new Stage();
//        stage.setTitle("QR Code");
//        ImageView imageView = new ImageView(SwingFXUtils.toFXImage(qrImage, null));
//        Scene scene = new Scene(new Group(imageView));
//        stage.setScene(scene);
//        stage.show();
//    } else {
//        // Handle case where no destination is selected
//        Alert alert = new Alert(AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText("Aucun utilisateur est séléctionné");
//        alert.setContentText("Séléctionnez un utilisateur SVP");
//        alert.showAndWait();
//    }   
//    }
}
