/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author BAZINFO
 */
public class AccueilMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
//      root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
//        root = FXMLLoader.load(getClass().getResource("affichefrontoffre.fxml"));
        
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Utri");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
