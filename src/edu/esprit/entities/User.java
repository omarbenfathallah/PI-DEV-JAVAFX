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
    private String email , password , nomU , prenom , type , confirm_password,tel;
    private String roles;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public User(int id, String email, String password, String nomU, String prenom, String type, String confirm_password, String tel, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nomU = nomU;
        this.prenom = prenom;
        this.type = type;
        this.confirm_password = confirm_password;
        this.tel = tel;
        this.roles = roles;
    }
  

    public User() {
    }

    public User(int id, String email, String password, String nomU, String prenom, String type, String confirm_password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nomU = nomU;
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
        return nomU;
    }

    public void setNom(String nomU) {
        this.nomU = nomU;
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
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", nom=" + nomU + ", prenom=" + prenom + ", type=" + type + ", confirm_password=" + confirm_password + ", tel=" + tel + ", roles=" + roles + '}';
    }

   
    
    
    
}
