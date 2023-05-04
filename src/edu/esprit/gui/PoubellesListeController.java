/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Poubelle;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


 /**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class PoubellesListeController implements Initializable {

    
    @FXML
    private ImageView ImageView_Poubelle;

    @FXML
    private AnchorPane card_form;

    @FXML
    private Button poubelAdd;

    @FXML
    private Spinner<?> poubelSpinner;

    @FXML
    private Label poubelleDemandeNum;

    @FXML
    private Label poubelleType;
    private Poubelle poubD;
private Image image;

    
public void setData(Poubelle poubD){
    this.poubD=poubD;
    poubelleType.setText(poubD.getName());
    poubelleDemandeNum.setText(String.valueOf(poubD.getId()));
    String path ="file:"+Poubelle.getImage();
image = new Image(path, 200, 85, false, true);
ImageView_Poubelle.setImage(image);
            
}






    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
