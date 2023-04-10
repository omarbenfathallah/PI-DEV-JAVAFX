/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.OffreDAO;
import edu.esprit.entities.Offre;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class FrontOfficeController implements Initializable {

    @FXML
    private VBox offersVBox;

    private OffreDAO offreService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        offreService = new OffreDAO();
        List<Offre> offres = offreService.getAllOffres();

        for (Offre offre : offres) {
            Label offreLabel = new Label(offre.toString());
            offreLabel.setStyle("-fx-font-size: 16px;");
            offersVBox.getChildren().add(offreLabel);
        }
    }

}
