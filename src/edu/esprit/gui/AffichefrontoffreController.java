/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.dao.classes.OffreDAO;
import edu.esprit.entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AffichefrontoffreController implements Initializable {

    @FXML
    private GridPane gridoffre;
    OffreDAO of = new OffreDAO();
    Offre o = new Offre();
    @FXML
    private TextField search;
    @FXML
    private Button Rech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherfront(1, 6);
    }

    public void afficherfront(int pageNumber, int itemsPerPage) {
        try {
            List<Offre> offres = of.getOffresByPage(pageNumber, itemsPerPage);

            gridoffre.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < offres.size(); i++) {
                // chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("offrefrontomar.fxml"));
                AnchorPane pane = loader.load();

                // passage de parametres
                OffrefrontomarController controller = loader.getController();
                controller.setEvenment(offres.get(i));
                controller.setIdoffre(offres.get(i).getId_offre());
                gridoffre.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }

            // Add navigation controls
            Button previousButton = new Button("Previous");
            Button nextButton = new Button("Next");

            previousButton.setOnAction(event -> {
                if (pageNumber > 1) {
                    afficherfront(pageNumber - 1, itemsPerPage);
                }
            });

            nextButton.setOnAction(event -> {
                if (offres.size() == itemsPerPage) {
                    afficherfront(pageNumber + 1, itemsPerPage);
                }
            });

            HBox navigationBox = new HBox(previousButton, nextButton);
            navigationBox.setAlignment(Pos.CENTER);

            gridoffre.add(navigationBox, 0, row + 1, 2, 1);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    

    private void RechercheO(int pageNumber, int itemsPerPage) {
        
    if (search.getText().isEmpty()) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Search is empty");
    alert.setHeaderText("Please enter a search term");
    alert.showAndWait();
    afficherfront(1, 6);
    return;   }   
//} else {
//    String searchText = search.getText();
//    if (searchText != of.chercherOffre(search.getText()) ) {
//        Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a search term.");
//        alert.showAndWait();
//        return;
//    }  else {
        
        try {
            List<Offre> offres = of.chercherOffre(search.getText());
               if (offres.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No results found");
            alert.setHeaderText("No results were found for the given search term");
            alert.showAndWait();
            return;
        }
            
            gridoffre.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < offres.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("offrefrontomar.fxml"));
                AnchorPane pane = loader.load();

                //passage de parametres
                OffrefrontomarController controller = loader.getController();
                controller.setEvenment(offres.get(i));
                gridoffre.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
            // Add navigation controls
            Button previousButton = new Button("Previous");
            Button nextButton = new Button("Next");

            previousButton.setOnAction(event -> {
                if (pageNumber > 1) {
                    afficherfront(pageNumber - 1, itemsPerPage);
                }
            });

            nextButton.setOnAction(event -> {
                if (offres.size() == itemsPerPage) {
                    afficherfront(pageNumber + 1, itemsPerPage);
                }
            });

            HBox navigationBox = new HBox(previousButton, nextButton);
            navigationBox.setAlignment(Pos.CENTER);

            gridoffre.add(navigationBox, 0, row + 1, 2, 1);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
         
    @FXML
    private void RechercheOffre(ActionEvent event) {
        RechercheO(1, 4);
    }

}
