/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import edu.esprit.dao.classes.Mailling;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import edu.esprit.dao.classes.UserService;

/**
 *
 * @author BAZINFO
 */
public class User {

    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String role;
    private String password;
    private String confirm_password; 
    private String numero;
    private String adresse;
    public String type;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public User(int id, String email, String firstname, String lastname, String role, String password, String confirm_password, String numero, String adresse, String type) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.password = password;
        this.confirm_password = confirm_password;
        this.numero = numero;
        this.adresse = adresse;
        this.type = type;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
    
    
    

    public User(int id, String email, String password, String firstname, String lastname, String type, String tel, String role, String adresse) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
        this.numero = numero;
        this.role = role;
        this.adresse = adresse;
    }

    public static String encrypMD5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigst = md.digest(data.getBytes());
            BigInteger num = new BigInteger(1, messageDigst);
            String hashtext = num.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String verifRole() {

        if (getEmail().contains("u-tri.tn")) //this.role="[\"ROLE_CITOYEN\",\"ROLE_ADMIN\"]";
        {
            this.role = "[\"ROLE_ADMIN\"]";
        } else {
            this.role = "[\"ROLE_CITOYEN\"]";
        }

        return role;
    }

    public String verifType() {

        if (getEmail().contains("u-tri.tn")) {
            this.type = "Admin";
        } else {
            this.type = "Citoyen";
        }

        return type;
    }

    public Integer rondomcode() {
        Random rand = new Random();
        int randomcode = rand.nextInt(999999);
        return randomcode;
    }

    public void forgetpass(String email) {
        UserService sp = new UserService();
        Mailling m = new Mailling();
        int code = rondomcode();

        if (sp.checkEmail(email).contains(email)) {
            Scanner s = new Scanner(System.in);
            m.sendemail(email, code);
            System.out.println("Enter code");
            int verifcode = s.nextInt();
            if (code == verifcode) {
                System.out.println("Enter new passwoed");
                String newpassword = s.next();
                if (!sp.Updatepassword(newpassword, email)) {
                    System.out.println("passwoed updated");
                } else {
                    System.out.println("passwoed wasn't updated");
                }

            } else {
                System.out.println("Rong code");
            }

        } else {
            System.out.println(email + " was not found in our database\ntry again");
        }

    }

    public boolean signup(String mailorusername, String password) {
        boolean check;
        UserService sp = new UserService();
        List<String> list;
        list = new ArrayList<>();
        list.add(mailorusername);
        list.add(encrypMD5(password));
        if (sp.checksignin(mailorusername, encrypMD5(password)).equals(list)) {
            check = true;
            System.out.println("correct credentials");

        } else {
            check = false;
            System.out.println("correct credentials needed");

        }
        return check;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
