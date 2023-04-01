/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author BAZINFO
 */
public class User {
    private int id ;
<<<<<<< HEAD
    private String email , password , nomU , prenom , type , confirm_password,tel;
=======
    private String email , password , nom , prenom , type , confirm_password,tel;
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
    private String roles;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

<<<<<<< HEAD
    public User(int id, String email, String password, String nomU, String prenom, String type, String confirm_password, String tel, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nomU = nomU;
=======
    public User(int id, String email, String password, String nom, String prenom, String type, String confirm_password, String tel, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
        this.prenom = prenom;
        this.type = type;
        this.confirm_password = confirm_password;
        this.tel = tel;
        this.roles = roles;
    }
  

    public User() {
    }

<<<<<<< HEAD
    public User(int id, String email, String password, String nomU, String prenom, String type, String confirm_password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nomU = nomU;
=======
    public User(int id, String email, String password, String nom, String prenom, String type, String confirm_password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
        this.prenom = prenom;
        this.type = type;
        this.confirm_password = confirm_password;
  
    }

   public  User(int id) {
       this.id = id;
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

    public String getNom() {
<<<<<<< HEAD
        return nomU;
    }

    public void setNom(String nomU) {
        this.nomU = nomU;
=======
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", nom=" + nomU + ", prenom=" + prenom + ", type=" + type + ", confirm_password=" + confirm_password + ", tel=" + tel + ", roles=" + roles + '}';
=======
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", type=" + type + ", confirm_password=" + confirm_password + ", tel=" + tel + ", roles=" + roles + '}';
>>>>>>> 7e79a6c50cf7d1dbb6a73661705e0415f93b8285
    }

   
    
    
    
}
