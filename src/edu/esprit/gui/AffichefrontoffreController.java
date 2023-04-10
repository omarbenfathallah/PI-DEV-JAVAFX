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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherfront();
    } 
    
     public void afficherfront(){
     try {
         List<Offre> offres = of.DisplayAllOffres();
         
         gridoffre.getChildren().clear();
         int row=0;
         int column=0;
         for (int i = 0; i <offres.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("offrefrontomar.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                OffrefrontomarController controller = loader.getController();
                controller.setEvenment(offres.get(i));
                controller.setIdoffre(offres.get(i).getId_offre());
                gridoffre.add(pane,column,row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
               
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }   
     
     
     }
     
     }
    

