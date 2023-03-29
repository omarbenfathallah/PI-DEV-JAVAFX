/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.entities;

/**
 *
 * @author BAZINFO
 */
public class Categories {
    private String nom,description;
    private int id_categorie;

    public Categories() {
    }

    public Categories(String nom, String description, int id_categorie) {
        this.nom = nom;
        this.description = description;
        this.id_categorie = id_categorie;
    }
     public Categories(int id_categorie,String nom, String description) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.description = description;
      
    }

    public Categories(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

   public  Categories(int id_categorie) {
         this.id_categorie = id_categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "Categories{" + "nom=" + nom +'}';
    }
    
    
}
