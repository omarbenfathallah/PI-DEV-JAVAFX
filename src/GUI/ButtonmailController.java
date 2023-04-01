/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class ButtonmailController implements Initializable {

    @FXML
    private Button mailbtn;
    @FXML
    private TextField from;
    @FXML
    private TextField pass;
    @FXML
    private TextField too;
    @FXML
    private TextField objmail;
    @FXML
    private TextArea descmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendbutton(ActionEvent event) {
    }
    
}
