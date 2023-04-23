/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import edu.esprit.entities.Offre;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
//import com.google.zxing.client.j2se.MatrixToImageWriter;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class QrcodeController implements Initializable {

    @FXML
    private ImageView qrimg;
    @FXML
    private Button btnAchat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         int size = 250;
        // TODO
    }

    public static void generateQrCode(Offre offre, String filePath) throws Exception {
        int size = 250;
        String details = "Nom : " + offre.getNom() + "\nPoints : " + offre.getPoints() + "\nDescription : " + offre.getDescription();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // encode the details string as a QR code
        BitMatrix bitMatrix = qrCodeWriter.encode(details, BarcodeFormat.QR_CODE, size, size);
        // create a new Image from the QR code matrix
        Image image = createQrCodeImage(bitMatrix);
        // save the Image to a file
        File file = new File(filePath);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
    }

    private static Image createQrCodeImage(BitMatrix bitMatrix) {
        int size = 250;
        // create a new WritableImage and PixelWriter
        WritableImage image = new WritableImage(size, size);
        PixelWriter writer = image.getPixelWriter();
        // set the image pixels based on the QR code matrix
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                writer.setColor(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        // return the Image
        return image;
    }

    public void setQrCodeImage(String filePath) {
        Image qrCodeImage = new Image(new File(filePath).toURI().toString());
        qrimg.setImage(qrCodeImage);
    }
}
