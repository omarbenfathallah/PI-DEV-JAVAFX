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
     private int id_categorie;
    private String nomC ,descriptionC;
   

    public Categories() {
    }

    public Categories(String nomC, String descriptionC, int id_categorie) {
        this.nomC = nomC;
        this.descriptionC = descriptionC;
        this.id_categorie = id_categorie;
    }
    
    
    
     public Categories(int id_categorie,String nomC, String descriptionC) {
        this.id_categorie = id_categorie;
        this.nomC = nomC;
        this.descriptionC = descriptionC;
      
    }

    public Categories(String nomC, String descriptionC) {
        this.nomC = nomC;
        this.descriptionC = descriptionC;
    }

   public  Categories(int id_categorie) {
         this.id_categorie = id_categorie;
    }

    public Categories(String nomC) {
        this.nomC=nomC;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nom) {
        this.nomC = nomC;
    }

    public String getDescriptionC() {
        return descriptionC;
    }

    public void setDescriptionC(String descriptionC) {
        this.descriptionC = descriptionC;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

//    @Override
//    public String toString() {
//        return "Categories{" + "id_categorie=" + id_categorie + ", nomC=" + nomC + ", descriptionC=" + descriptionC + '}';
//    }

    @Override
    public String toString() {
        return nomC ;
    }

  

  
}
