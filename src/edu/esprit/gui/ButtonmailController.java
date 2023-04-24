/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

    public static void send(String from, String pwd, String to, String sub, String msg) {
        //Propriétés
        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "465");
        //Session
        Session s = Session.getDefaultInstance(p,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pwd);
            }
        });
        //composer le message
        try {
            MimeMessage m = new MimeMessage(s);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(sub);
            m.setText(msg);
            //envoyer le message
            Transport.send(m);
            System.out.println("Message envoyé avec succès");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sendbutton(ActionEvent event) {
        String fromAddress = from.getText();
        String password = pass.getText();
        String toAddress = too.getText();
        String subject = objmail.getText();
        String message = descmail.getText();

        send(fromAddress, password, toAddress, subject, message);
    }

}
