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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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

        afficherfront();
    }

public void afficherfront() {
    try {
        List<Offre> offres = of.DisplayAllOffres();
        gridoffre.getChildren().clear();
       
       
        int offresPerPage = 6; // Number of offres to display per page
        int numPages = (int) Math.ceil(offres.size() / (double) offresPerPage); // Calculate the number of pages needed

        Pagination pagination = new Pagination(numPages, 0); // Create a new Pagination object with the correct number of pages
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                 int column = 0;
                  int row = 0;
                // Create a GridPane to hold the offres for the current page
                GridPane pageGrid = new GridPane();
                pageGrid.setHgap(10);
                pageGrid.setVgap(10);
                // Calculate the start and end index of the offres for the current page
                int startIndex = pageIndex * offresPerPage;
                int endIndex = Math.min(startIndex + offresPerPage, offres.size());
                // Add the offres to the GridPane for the current page
                for (int i = startIndex; i < endIndex; i++) {
                    // chargement dynamique d'une interface
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("offrefrontomar.fxml"));
                    AnchorPane pane = null;
                    try {
                        pane = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AffichefrontoffreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // passage de parametres
                    OffrefrontomarController controller = loader.getController();
                    try {
                        controller.setEvenment(offres.get(i));
                    } catch (IOException ex) {
                        Logger.getLogger(AffichefrontoffreController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    controller.setIdoffre(offres.get(i).getId_offre());
                    pageGrid.add(pane, column, row);
                    column++;
                    if (column > 2) {
                        column = 0;
                        row++;
                    }
                }
                return pageGrid; // Return the GridPane containing the offres for the current page
            }
        });

        gridoffre.add(pagination, 0, 1, 2, 1); // Add the pagination control to the gridoffre GridPane

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}
    
//    public void afficherfront() {
//        try {
//            List<Offre> offres = of.DisplayAllOffres();
//            gridoffre.getChildren().clear();
//
//            // Create a Pagination control with 3 items per page
//            Pagination pagination = new Pagination((int) Math.ceil(offres.size() / 3.0));
//            pagination.setPageFactory(pageIndex -> {
//                // Create a new grid pane for each page
//                GridPane pageGrid = new GridPane();
//                pageGrid.setHgap(10);
//                pageGrid.setVgap(10);
//
//                // Calculate the start and end index of the items for the current page
//                int startIndex = pageIndex * 3;
//                int endIndex = Math.min(startIndex + 3, offres.size());
//
//                // Add the items for the current page to the grid pane
//                int column = 0;
//                int row = 0;
//                for (int i = startIndex; i < endIndex; i++) {
//                    // Load the FXML file for each item
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("offrefrontomar.fxml"));
//                    AnchorPane pane = null;
//                    try {
//                        pane = loader.load();
//                    } catch (IOException ex) {
//                        Logger.getLogger(AffichefrontoffreController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    // Set the data for each item
//                    OffrefrontomarController controller = loader.getController();
//                    try {
//                        controller.setEvenment(offres.get(i));
//                    } catch (IOException ex) {
//                        Logger.getLogger(AffichefrontoffreController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    controller.setIdoffre(offres.get(i).getId_offre());
//
//                    // Add the item to the grid pane
//                    pageGrid.add(pane, column, row);
//                    column++;
//                    if (column > 1) {
//                        column = 0;
//                        row++;
//                    }
//                }
//
//                return pageGrid;
//            });
//
//            // Add the pagination control to the scene
//            
//            VBox root = new VBox(pagination);
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    private void RechercheO(ActionEvent event) {

        if (search.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Search is empty");
            alert.setHeaderText("Please enter a search term");
            alert.showAndWait();
            afficherfront();
            return;
        }

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

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void RechercheOffre(ActionEvent event) {
        RechercheO(event);
    }

}
